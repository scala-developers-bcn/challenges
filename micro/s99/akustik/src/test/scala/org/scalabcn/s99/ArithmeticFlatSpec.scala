package org.scalabcn.s99

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalabcn.s99.ArithmeticExtensions._

@RunWith(classOf[JUnitRunner])
class ArithmeticFlatSpec extends FlatSpec with ShouldMatchers {

  def time[R](f: => R): (R, Long) = {
    val t0 = System.nanoTime()
    val r = f
    val t1 = System.nanoTime()
    return (r, t1 - t0)
  }

  "isPrime" should "return true for 1" in {
    1.isPrime should be(true)
  }

  it should "return true for 2" in {
    2.isPrime should be(true)
  }

  it should "return false for 4" in {
    4.isPrime should be(false)
  }

  it should "return true for 5" in {
    5.isPrime should be(true)
  }

  it should "return false for 9" in {
    9.isPrime should be(false)
  }

  it should "return true for 23" in {
    23.isPrime should be(true)
  }

  it should "return false for 13*13" in {
    (13*13).isPrime should be(false)
  }

  it should "return true for 2147483629" in {
    2147483629.isPrime should be(true)
  }

  "gcd" should "return 9 for 36 and 63" in {
     gcd(36, 63) should be(9)
  }

  "isComprimeTo" should "return true for 35 comprime to 64" in {
    35.isCoprimeTo(64)
  }

  "totient" should "return 4 for 10" in {
    10.totient should be(4)
    println("The result of totient for 10090 for " + time(10090.totient))
  }

  "totient2" should "return 4 for 10" in {
    10.totient2 should be(4)
  }

  it should "return 4032 for 10090" in {
    10090.totient2 should be(4032)
    println("The result of totient2 for 10090 for " + time(10090.totient2))
  }

  "primeFactors" should "return List(3,3,5,7) for 315" in {
    315.primeFactors should be(List(3, 3, 5, 7))  
  }

  "primeFactors" should "return List(2) for 2" in {
    2.primeFactors should be(List(2))
  }

  "primeFactorMultiplicity" should "return List((3,2), (5,1), (7,1)) for 315 " in {
    315.primeFactorMultiplicity should be(List((3,2), (5,1), (7,1)))
  }
}
