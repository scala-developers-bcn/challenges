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

  "flatten (P07)" should "flat the input into a single list" in {
    forAll { (list: List[List[Int]]) =>
      flatten(list) should be (list.flatten)
    }
  }

  "compress (P08)" should "replace repeated consecutive elements with a single instance" in {
    forAll { (list: List[Boolean]) =>
      val compressed = compress(list)
      def isCompression[T](compressed: List[T], expanded: List[T]): Boolean = (compressed, expanded) match {
        case (Nil, Nil) => true
        case (x :: xs, y :: ys) if (x == y) => isCompression(xs, ys.dropWhile(_ == y))
        case _ => false
      }
      isCompression(compressed, list) should be (true)

      // Check if it's maximal compression
      compressed.sliding(2) forall {
        case List(x) => true
        case List(x, y) => x != y
      } should be (true)
    }
  }

  "pack (P09)" should "group repeated consecutive elements into sublists" in {
    forAll { (list: List[Boolean]) =>
      val packed = pack(list)
      packed forall (xs => xs forall (_ == xs.head)) should be (true)
      packed.flatten should be (list)
    }
  }

  "encode (P10)" should "perform run-length encoding" in {
    forAll { (list: List[Boolean]) =>
      val encoded = encode(list)
      encoded flatMap { case (num: Int, value: Boolean) => List.fill(num)(value) } should be (list)
    }
  }
}
