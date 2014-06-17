package org.gmd

import akka.actor._
import java.io._
import akka.routing._
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit

object actors {
  
  type OptionalPassword = (String, Option[String])
  
  case class Computation(dictionaryPath: String, hashes: List[String])
  case class AddPassword(pwd: String)
  case class QueryForPassword(hashes: List[String])
  case class PasswordAnswer(answers: List[OptionalPassword])
  
  class FacadeActor(pf: ActorPasswordFinder, nHashers: Int = 1) extends Actor {
    val hashAccActor = context.actorOf(RoundRobinPool(nHashers).props(Props(classOf[HashAccumulatorActor], pf)), "hashers")
    val lineReaderActor = context.actorOf(Props(classOf[actors.LineReaderActor], hashAccActor), "reader")
    var nAnswers = 0
    var allAnswers: List[OptionalPassword] = Nil
    var answerTo = self

    def receive = {
      case Computation(p, h) => {
        answerTo = sender
        lineReaderActor ! Computation(p, h)
      }
      case PasswordAnswer(answers) => {
        nAnswers = nAnswers + 1
        allAnswers = answers ::: allAnswers
        if(nHashers == nAnswers) {
          answerTo ! PasswordAnswer(allAnswers)
        }
      }
      case other => println(this + ": " + other)
    }
  }

  class HashAccumulatorActor(pf: ActorPasswordFinder) extends Actor {
    var dict = Map[String, String]()
    def receive = {
      case AddPassword(pwd) => dict = dict + (pf.computeHash(pwd) -> pwd)
      case QueryForPassword(hashes) => sender ! PasswordAnswer(hashes.map(hash => (hash, dict get hash)))
      case other => println(this + ": " + other)
    }
  }

  class LineReaderActor(hashAccActor: ActorRef) extends Actor with InOutOps {
    def receive = {
      case Computation(path, hashes) => {
        using(new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
          br =>
            {
              var line: String = null
              do {
                line = br.readLine()
                if(line != null) {
                  hashAccActor ! AddPassword(line)
                } else {
                  hashAccActor.tell(Broadcast(QueryForPassword(hashes)), sender)
                }
              } while (line != null)
            }
        }
      }
      case other => println(this + ": " + other)
    }
  }
}

class ActorPasswordFinder extends PasswordFinder {

  override def findMatchesInDictionary(dictionaryPath: String, hashes: List[String]): Set[(String, Option[String])] = {   
    val system = ActorSystem("PasswordFinderSystem") 
    val facadeActor = system.actorOf(Props(classOf[actors.FacadeActor], this, 4))
    val inbox = Inbox.create(system)
    var res: Set[actors.OptionalPassword] = Set()

    inbox.send(facadeActor, actors.Computation(dictionaryPath, hashes))
    inbox.receive(Duration(1, TimeUnit.MINUTES)) match {
      case actors.PasswordAnswer(answers) => {
        res = answers.groupBy(answer => answer._1).
          map(group => group._2.fold((group._1, None))((l, r) => {
            if(!l._2.isEmpty) l else r
          })).toSet
      }
    }
    
    system.shutdown
    res
  }
}

