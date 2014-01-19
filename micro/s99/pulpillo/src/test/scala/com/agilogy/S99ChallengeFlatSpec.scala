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

  "P11" should "Run modify length encode of a list " in {
    s99.encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  }

  "P12" should "Decode a encoded list of problem 10" in {
    s99.decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) should be (List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  }

  "P13" should "Run-length encoding of a list (direct solution)." in {
    s99.encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be ( List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

  "P14" should "Duplicate the elements of a list." in {
    s99.duplicate(List('a, 'b, 'c, 'c, 'd)) should be (List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }

  "P15" should "Duplicate the elements of a list a given number of times." in {
    s99.duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should be (List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
  }

  "P16" should "Drop every Nth element from a list." in {
    s99.drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
  }

  "P17" should "Split a list into two parts" in {
    s99.split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be ((List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  "P18" should "Extract a slice from a list." in {
    s99.slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (List('d, 'e, 'f, 'g))
  }
  "P19" should "Rotate a list N places to the left." in {
    s99.rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
  }

  it should "Rotate a list N places to the left with negative number" in {
    s99.rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i))
  }

  "P20" should "Remove the Kth element from a list." in {
    s99.removeAt(1, List('a, 'b, 'c, 'd)) should be ((List('a, 'c, 'd),'b))
  }

  it should "Remove the nth element from a two element list" in {
    s99.removeAt(1, List('a, 'b )) should be ((List('a),'b))
  }
}


