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
    context.watch(hashAccActor)
    context.watch(lineReaderActor)
    var nAnswers = 0
    var allAnswers: List[OptionalPassword] = Nil
    var answerTo = self

    def receive = {
      case Computation(p, h) => {
        answerTo = sender
        lineReaderActor ! Computation(p, h)
      }
      case Terminated(ref) => println("terminated: " + ref)
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
        println(s"reading $path")
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
    val facadeActor = system.actorOf(Props(classOf[actors.FacadeActor], this, 2))
    val inbox = Inbox.create(system)

    inbox.send(facadeActor, actors.Computation(dictionaryPath, hashes))
    inbox.receive(Duration(30, TimeUnit.SECONDS)) match {
      case actors.PasswordAnswer(answers) => {
        println(answers)
        println(answers.groupBy(a => a._1))
      }
    }
    
    
    
    system.shutdown
    
    Set()
  }
}

object Main {
  def main(args: Array[String]) {
    val pf = new ActorPasswordFinder
    pf.findMatchesInDictionary("target/smoke.dict", List("1234", "abcd").map(pf.computeHash));
  }
}

