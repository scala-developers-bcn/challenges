package org.gmd

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorSystem
import java.io.FileInputStream
import java.io.InputStreamReader
import java.io.BufferedReader
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.language.implicitConversions
import akka.actor.ActorLogging
import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest

object actors {
  case class InputFile(path: String)
  case class Password(pwd: String)
  case class Query(hash: String)
  case class Answer(pair: (String, Option[String]))
  case object DictionaryIsReady

  class HashAccActor extends Actor {
    var dict = Map[String, String]()
    def receive = {
      case Password(null) => {
        println("Dictionary is loaded!")
        sender ! DictionaryIsReady
      }
      case Password(pwd) => dict = dict + (computeHash(pwd) -> pwd)
      case Query(hash) => sender ! Answer(hash, dict get hash)
    }

    def computeHash(pwd: String): String = {
      DatatypeConverter.printBase64Binary(MessageDigest.getInstance("SHA-256").digest(pwd.getBytes))
    }
  }

  class ReaderActor extends Actor with InOutOps {

    val hashAcc = context.actorOf(Props[HashAccActor], name = "hasher")
    var accQueries = List[Query]()
    var accAnswers = List[(String, Option[String])]()

    def receive = {
      case InputFile(path) => {
        println(s"reading $path")
        using(new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
          br =>
            {
              var line: String = null
              do {
                line = br.readLine()
                hashAcc ! Password(line)
              } while (line != null)
            }
        }
      }
      case Query(hash) => {
        accQueries = Query(hash) :: accQueries
      }
      case DictionaryIsReady => {
        println("making queries..")
        accQueries.foreach(hashAcc ! _)
      }
      case Answer(pair) => {
        accAnswers = pair :: accAnswers
        if(accAnswers.size == accQueries.size) {
          println(accAnswers)
          context.system.shutdown
        }
      }
    }
  }
}

class ActorPasswordFinder extends PasswordFinder {

  override def findMatchesInDictionary(dictionaryPath: String, hashes: List[String]): Set[(String, Option[String])] = {
    val system = ActorSystem("PasswordFinderSystem")
    val reader = system.actorOf(Props[actors.ReaderActor], name = "reader")
    reader ! actors.InputFile(dictionaryPath)
    hashes foreach(reader ! actors.Query(_))
    system.awaitTermination(20 seconds)
    Set()
  }

}

object Main {
  def main(args: Array[String]) {
    val pf = new ActorPasswordFinder
    pf.findMatchesInDictionary("target/smoke.dict", List("1234", "abcd").map(pf.computeHash));
  }
}

