package com.agilogy

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class S99ChallengeFlatSpec extends FlatSpec with ShouldMatchers {

  val s99 = new S99Challenge

  "P01" should "return the last element of a list" in {
    s99.last(List(1, 1, 2, 3, 5, 8)) should be (8)
  }

  it should "return Nil for the last element of an empty list" in {
    s99.last(List()) should be (Nil)    
  }

  "P02" should "return the penultimate element of a list" in {
    s99.penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
  }

    it should "return Nil in an only one item list" in {
    s99.penultimate(List(1)) should be (Nil)
  }

  it should "return Nil for the penultimate element of an empty list" in {
    s99.penultimate(List()) should be (Nil)
  }

"P03" should "return the nth element in the list" in {
    s99.nth(2,List(1, 1, 2, 3, 5, 8)) should be (2)
  }

  it should "return nil if pos is high than size" in {
    s99.nth(6,List(1,1,2,3,5,8)) should be (Nil)
  }

  "P04" should "return the length of the list" in {
    s99.length(List(1, 1, 2, 3, 5, 8)) should be (6)
  }

 "P05" should "return the reverse list" in {
    s99.reverse(List(1, 1, 2, 3, 5, 8)) should be ((List(8,5,3,2,1,1)))
  }

  "P06" should "Find if a list is a palindrome" in {
    s99.isPalindrome(List(1, 2,3,2,1)) should be (true)
  }

 "P07" should "return a flatten nested list" in {
    s99.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
  }

   "P08" should "Eliminate consecutive duplicates of list elements" in {
    s99.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List('a, 'b, 'c, 'a, 'd, 'e))
  }
   it should "return Nil for an empty list" in {
    s99.compress(List()) should be (Nil)
  }

  it should "return the same list with a no duplicates list" in {
    s99.compress(List('a, 'b, 'c, 'a, 'd, 'e)) should be (List('a, 'b, 'c, 'a, 'd, 'e))
  }
    "P09" should "Pack consecutive repetitive elements in sublists" in {
    s99.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
   }

     "P10" should "Run length encode of a list" in {
    s99.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
   }


}

