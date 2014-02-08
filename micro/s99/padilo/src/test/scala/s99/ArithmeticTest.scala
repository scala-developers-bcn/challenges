package s99

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import scala.language.implicitConversions

class ArithmeticTest extends FlatSpec with ShouldMatchers {
  "P33.isPrime" should "return true on 7" in {
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

}