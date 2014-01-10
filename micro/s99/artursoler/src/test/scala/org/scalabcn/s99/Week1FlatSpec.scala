package org.scalabcn.s99

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.PropertyChecks
import org.scalabcn.s99.Week1._

class Week1FlatSpec extends FlatSpec with Matchers with PropertyChecks {
  "last (P01)" should "return last element of list" in {
    forAll { (init: List[Int], end: Int) =>
      val complete = init ::: List(end)
      last(complete) should be (end)
    }
  }

  "penultimate (P02)" should "return the last but one element" in {
    forAll { (init: List[Int], target: Int, last: Int) =>
      val complete = init ::: List(target) ::: List(last)
      penultimate(complete) should be (target)
    }
  }

  "nth (P03)" should "return the element at position n" in {
    forAll { (first: List[Int], second: List[Int], between: Int) =>
      val complete = first ::: (between :: second)
      nth(first.length, complete) should be (between)
    }
  }

  "length (P04)" should "return the length of the list" in {
    forAll { (list: List[Int]) =>
      Week1.length(list) should be (list.length)
    }
  }

  "reverse (P05)" should "return the original list reversed" in {
    forAll { (list: List[Int]) =>
      reverse(list) should be (list.reverse)
    }
  }

  "isPalindrome (P06)" should "return true only when the argument is a palindrome" in {
    forAll { (list: List[Int]) =>
      isPalindrome(list) should equal (list == list.reverse)
      val mirrored = list ::: list.reverse
      isPalindrome(mirrored) should be (true)
      if (list.nonEmpty) {
        val semimirrored = list ::: list.reverse.tail
        isPalindrome(semimirrored) should be (true)
      }
    }
  }

}
