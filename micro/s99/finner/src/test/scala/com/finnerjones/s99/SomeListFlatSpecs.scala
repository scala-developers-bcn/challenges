package com.finnerjones.s99

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.finnerjones.s99.ListUtils._

/*
 * The S-99 scala katas are here:
 * http://aperiodic.net/phil/scala/s-99/
 * 
 */
class SomeListFlatSpecs extends FlatSpec with Matchers {

  // P01 on S99

  "last(List(1,2,3,4,5,6,7,8))" should "result in 8" in {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)
    last(l) should be(8)
  }

  "last(List(one,two,three,four,five)" should "results in (five)" in {
    val l = List("one", "two", "three", "four", "five")
    last(l) should be("five")
  }

  // P02 on S99
  "penultimate(List(1,2,3,4,5,6,7,8))" should "result in (7)" in {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)
    penultimate(l) should be(7)
  }

  "penultimate(List(four,three,seven,four,nine))" should "result in (four)" in {
    val l = List("four", "three", "seven", "four", "nine")
    penultimate(l) should be("four")
  }

  // P03 on S99
  "nth(List(3, List(1,2,3,4,5,6,7,8)))" should "return (4)" in {
    val l = List(1, 2, 3, 4, 5, 6, 7, 8)
    nth(3, l) should be(4)
  }

  "nth(List(4, List(9,6,7,3,6,1,2)))" should "return (6)" in {
    val l = List(9, 6, 7, 3, 6, 1, 2)
    nth(4, l) should be(6)
  }

  "nth(List(2, List(the brown fox jumped)))" should "return (fox)" in {
    val l = List("the", "brown", "fox", "jumped")
    nth(2, l) should be("fox")
  }

  // P04 on S-99
  "length(List(3,4,5,6,2,7))" should "return (6)" in {
    val l = List(3, 4, 5, 6, 2, 7)
    lngth(l) should be(6)
  }

  "length(List(sixty, five, days, of, static))" should "return (5)" in {
    val l = List("sixty", "five", "days", "of", "static")
    lngth(l) should be(5)
  }

  "lengthTailRecursive(List(sixty, five, days, of, static))" should "return (5)" in {
    val l = List("sixty", "five", "days", "of", "static")
    lengthTailRecursive(l) should be(5)
  }

  // P05
  "reverse(List(1,2,3,4))" should "return List(4,3,2,1)" in {
    val l = List(1, 2, 3, 4)
    reverse(l) should be(List(4, 3, 2, 1))
  }

  "reverse(List(sixty five days of static))" should "return List(static, of, days, five, sixty)" in {
    val l = List("sixty", "five", "days", "of", "static")
    reverse(l) should be(List("static", "of", "days", "five", "sixty"))
  }

  // P06
  "isPalindrome(List(1,2,3,2,1))" should "return (true)" in {
    val l = List(1, 2, 3, 2, 1)
    isPalindrome(l) should be(true)
  }

  // P07
  "flatten(List(List(1,1),2,List(3, List(5,8)))" should "return List(1,1,2,3,5,8)" in {
    val l = List(List(1, 1), 2, List(3, List(5, 8)))
    flatten(l) should be(List(1, 1, 2, 3, 5, 8))
  }

  // P08
  "compressRecursive(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List('a', 'b', 'c', 'a', 'd', 'e')" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      compressRecursive(l) should be(List('a', 'b', 'c', 'a', 'd', 'e'))
    }

  // P08
  "compressTailRecursive(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List('a', 'b', 'c', 'a', 'd', 'e')" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      compressTailRecursive(l) should be(List('a', 'b', 'c', 'a', 'd', 'e'))
    }

  // P08
  "compressFunctional(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List('a', 'b', 'c', 'a', 'd', 'e')" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      compressFunctional(l) should be(List('a', 'b', 'c', 'a', 'd', 'e'))
    }

  // P09
  "pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))" should
    "return List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      pack(l) should be(List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e')))
    }

  // P09
  "pack2(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))" should
    "return List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      pack2(l) should be(List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e')))
    }

  // P10
  "encode(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      encode(l) should be(List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')))
    }

  // P10
  "encode2(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      encode2(l) should be(List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')))
    }

}