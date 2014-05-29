package chriszen

import java.io.{InputStreamReader, BufferedReader, InputStream, FileInputStream}
import scala.io.Source
import javax.xml.bind.DatatypeConverter
import java.security.MessageDigest
import scala.collection.mutable.ArrayBuffer

object MultiHashEval {
  val hashAlgorithm = "SHA-256"
  val charsetName = "UTF-8"
  val dictResource = "/cain.txt"

  def main(args: Array[String]) {

    val hashes = if (args.length > 0)
      Source.fromFile(args(0), charsetName).getLines
    else
      loadHashesFromDictionary(
        getClass.getResourceAsStream(dictResource), charsetName, hashAlgorithm)
    
    val inputStream = if (args.length > 1)
      new FileInputStream(args(1))
    else
      getClass.getResourceAsStream(dictResource)

    val passwordFinder = new chriszen.multihash.MapPasswordFinder
    
    time("Loading dictionary ...") {
      passwordFinder.loadDictionary(inputStream, charsetName, hashAlgorithm)
    }

    time("Looking for passwords ...") {
      val hits = hashes.map { hash =>
        passwordFinder.findPassword(hash).fold(0)(pass => 1)
      }.sum

      println(s"Found $hits passwords out of " + hashes.size)
    }

    /*time("Looking for passwords ...") {
      hashes.foreach { hash =>
        passwordFinder.findPassword(hash).foreach { password =>
          println(s"The password for '$hash' is $password")
        }
      }
    }*/
  }

  def loadHashesFromDictionary(inputStream: InputStream,
                 charsetName: String = "UTF-8",
                 hashAlgorithm: String = "SHA-256") = {

    val bis = new BufferedReader(new InputStreamReader(inputStream, charsetName))

    val hashes = new ArrayBuffer[String]()

    var s: String = null
    while ({ s = bis.readLine(); s != null })
      hashes += DatatypeConverter.printBase64Binary(
        MessageDigest.getInstance(hashAlgorithm).digest(s.getBytes))

    hashes
  }

  // Adapted from http://stackoverflow.com/questions/15436593/how-to-measure-and-display-the-running-time-of-a-single-test
  def time[T](title: String)(code: => T): T = {
    println(title)
    val start = System.currentTimeMillis
    val x = code
    val elapsed = ((System.currentTimeMillis - start) / 1000.0)
    println("Done in %.3f secs" format elapsed)
    x
  }
}
