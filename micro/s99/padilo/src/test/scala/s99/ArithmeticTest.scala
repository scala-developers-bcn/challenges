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

  

}