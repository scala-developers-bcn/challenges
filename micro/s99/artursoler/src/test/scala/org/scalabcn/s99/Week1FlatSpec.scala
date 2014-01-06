package org.scalabcn.s99

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.PropertyChecks
import org.scalabcn.s99.Week1.P01

class Week1FlatSpec extends FlatSpec with Matchers with PropertyChecks {
  "Solution 1" should "return last element of list" in {
    forAll { (init: List[Int], last: Int) =>
      val complete = (last :: init).reverse
      P01.trivial(complete) should equal (last)
      P01.recursive(complete) should equal (last)
    }
  }
}
