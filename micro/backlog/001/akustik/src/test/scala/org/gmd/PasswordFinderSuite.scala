package org.gmd

import org.scalatest._
import scala.io.Source
import java.io.PrintWriter
import scala.io.Codec
import java.io.File

trait PasswordFinderSuite extends FlatSpec with Matchers with InOutOps with BeforeAndAfterAll {

  def subjectUnderTest: PasswordFinder
  def suiteName: String

  val smokePasswords = List("1234", "abcd", "kkkk")
  val smokeDictionaryPath = "target/smoke.dict"

  val hugeDictionaryPath = "target/huge.dict"

  val reallyGoodPassword = "reallyGoodPassword:_74823423"
    
  override def beforeAll() {
    writeLinesToTargetFile(smokePasswords, smokeDictionaryPath)
    writeLinesToTargetFile((1 to 1000000).map(_.toString).toList, hugeDictionaryPath)
  }

  "A password finder" should "find a passwords when they exist in the dictionary" in {
    val pf = subjectUnderTest
    val matches = pf.findMatchesInDictionary(smokeDictionaryPath, smokePasswords.map(pf.computeHash))
    matches.map(_._2).flatten should be(smokePasswords.toSet)
  }

  it should "not find a password when it does not exist in the dictionary" in {
    val pf = subjectUnderTest
    val matches = pf.findMatchesInDictionary(smokeDictionaryPath, List(pf.computeHash(reallyGoodPassword)))
    matches.map(_._2).flatten should be(Set())
  }

  it should "try to find all the requested hashes" in {
    val pf = subjectUnderTest
    val matches = pf.findMatchesInDictionary(smokeDictionaryPath, List(pf.computeHash(reallyGoodPassword)))
    matches.map(_._1) should be(Set(pf.computeHash(reallyGoodPassword)))
  }

  it should "have good performance" in {
    val pf = subjectUnderTest
    val hashesToFind = List("1", "600", "999999", "notExisting", "1234", "56778").map(pf.computeHash)
    val matches = time("huge") {
      pf.findMatchesInDictionary(hugeDictionaryPath, hashesToFind)
    }
    matches.map(_._2).flatten should be(Set("56778", "1234", "1", "999999", "600"))
  }

  def time[T](title: String)(code: => T): T = {
    val start = System.currentTimeMillis
    val x = code
    val elapsed = ((System.currentTimeMillis - start) / 1000.0)
    println(s"[$suiteName] Huge: Done in %.3f secs" format elapsed)
    x
  }

  def writeLinesToTargetFile(lines: List[String], path: String)(implicit codec: Codec) = {
    using(new PrintWriter(path)) {
      _.write(lines.mkString(System.lineSeparator))
    }
  }

}