package org.scalabcn.s99

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalabcn.s99.ArithmeticExtensions._

@RunWith(classOf[JUnitRunner])
class ArithmeticFlatSpec extends FlatSpec with ShouldMatchers {

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
 
}
