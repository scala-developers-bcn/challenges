package org.scalabcn.s99

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.PropertyChecks
import org.scalabcn.s99.Week1._

class Week1FlatSpec extends FlatSpec with Matchers with PropertyChecks {
  "Last (P01)" should "return last element of list" in {
    forAll { (init: List[Int], end: Int) =>
      val complete = init ::: List(end)
      last(complete) should be (end)
    }
  }

  "Penultimate (P02)" should "return the last but one element" in {
    forAll { (init: List[Int], target: Int, last: Int) =>
      val complete = init ::: List(target) ::: List(last)
      penultimate(complete) should be (target)
    }
  }

  "Nth (P03)" should "return the element at position n" in {
    forAll { (first: List[Int], second: List[Int], between: Int) =>
      val complete = first ::: (between :: second)
      nth(first.length, complete) should be (between)
    }
  }

  "Length (P04)" should "return the length of the list" in {
    forAll { (list: List[Int]) =>
      Week1.length(list) should be (list.length)
    }
  }

  "Reverse (P05)" should "return the original list reversed" in {
    forAll { (list: List[Int]) =>
      reverse(list) should be (list.reverse)
    }
  }

}
