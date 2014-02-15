package s99

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import scala.language.implicitConversions

class ArithmeticTest extends FlatSpec with ShouldMatchers {
  "P31.isPrime" should "return true on 7" in {
    7.isPrime should be(true)
  }

  it should "return false on 4" in {
    4.isPrime should be(false)
  }

  it should "return false on 1" in {
    1.isPrime should be(false)
  }

  it should "return false on 0" in {
    0.isPrime should be(false)
  }

  it should "return true on -2" in {
    -2.isPrime should be(true)
  }

  "P32.gcd" should "return 9 on (36, 63)" in {
    P32.gcd(36, 63) should be(9)
  }

  it should "return 9 on (63, 36)" in {
    P32.gcd(63, 36) should be(9)
  }

  it should "return 33 on (3300, 33)" in {
    P32.gcd(3300, 33) should be(33)
  }

  "P33 35.isCoprimeTo(64)" should "be true" in {
    35.isCoprimeTo(64) should be(true)
  }

  "P33 36.isCoprimeTo(63)" should "be false" in {
    35.isCoprimeTo(64) should be(true)
  }

  "P34 10.totient" should "be 4" in {
    10.totient should be(4)
  }

  "P34 1.totient" should "be 1" in {
    1.totient should be(1)
  }

  "P34 0.totient" should "be 0" in {
    0.totient should be(0)
  }

  "P35 315.primeFactors" should "be List(3, 3, 5, 7)" in {
    315.primerFactors should be(List(3, 3, 5, 7))
  }

  "P35 2.primeFactors" should "be List(2)" in {
    2.primerFactors should be(List(2))
  }

  "P35 1.primeFactors" should "be Nil" in {
    1.primerFactors should be(Nil)
  }

  "315.primeFactorMultiplicity" should "be List((3,2), (5,1), (7,1))" in {
    315.primeFactorMultiplicity should be(List((3,2), (5,1), (7,1)))
  }
}