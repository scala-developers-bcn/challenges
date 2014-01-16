package com.agilogy

import org.scalatest._
import collection.mutable.Stack


class S99ChallengeFlatSpec extends FlatSpec with Matchers {

  val s99 = new S99Challenge

  "P01" should "return the last element of a list" in {
    s99.last(List(1, 1, 2, 3, 5, 8)) should be (8)
  }

  it should "return Nil for the last element of an empty list" in {
    s99.last(List()) should be (Nil)    
  }

  "P02" should "return the penulimate element of a list" in {
    s99.penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
  }

  it should "return Nil for the penultimate element of an empty list" in {
    s99.penultimate(List()) should be (Nil)
  }

  "P03" should "return the nth element of a list" in {
    s99.nth(2, List(1, 1, 2, 3, 5, 8)) should be (2)
    s99.nth(0, List(1, 1, 2, 3, 5, 8)) should be (1)
    s99.nth(5, List(1, 1, 2, 3, 5, 8)) should be (8)
  }

  it should "throw IndexOutOfBoundsException in case empty list" in {
    intercept[IndexOutOfBoundsException] {
      s99.nth(5, List())
    }
  }

  it should "throw IndexOutOfBoundsException in case " in {
    intercept[IndexOutOfBoundsException] {
      s99.nth(5, List())
    }
  }

  "P04" should "return length of a list " in {
    s99.length(List(2,3,4,5)) should be (4)
  }

  it should "return 0 for empty list" in {
    s99.length(List()) should be (0)
  }

  "P05" should "return the reversed list" in {
    s99.reverse(List(1, 1, 2, 3, 5, 8)) should be (List(8, 5, 3, 2, 1, 1))
  }

  it should "return Nil for empty List" in {
    s99.reverse(List()) should be (Nil)
  }

  "P06" should "return true if is a palindrom" in {
    s99.isPalindrome(List(1,2,3,2,1)) should be (true)
  }

  it should "return true for empty list" in {
    s99.isPalindrome(List()) should be (true)
  }

  it should "return true for just one element list" in {
    s99.isPalindrome(List(1)) should be (true)
  }

  it should "return false if is not a palindrom" in {
    s99.isPalindrome(List(1,2,3,3,1)) should be (false)
  }

  "P07" should "return the list flatten" in {
    s99.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
  }

  it should "return Nil on an empty list" in {
    s99.flatten(List()) should be (List())
  }

  it should "return normal list when list is flatten" in {
    s99.flatten(List(1,1,3,2)) should be (List(1,1,3,2))
  }

  "P08" should "eliminate consecutive duplicates of the list elements" in {
    s99.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List('a, 'b, 'c, 'a, 'd, 'e))
  }

  it should "return Nil on an empty list" in {
    s99.compress(List()) should be (List())
  }

  "P09" should "Pack consecutive duplicates of list elements into sublists" in {
    s99.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (
      List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
  }

  "P10" should "Run-length encoding of a list" in {
    s99.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (
      List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

  "P11" should "Modified run-length encoding" in {
    s99.encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (
      List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  }

  "P12" should "Decode a run-length encoded list" in {
    s99.decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) should be (
      List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  }

  "P13" should "Run-length encoding of a list (direct solution)." in {
    s99.encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (
      List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

  "P14" should "Duplicate the elements of a list" in {
    s99.duplicate(List('a, 'b, 'c, 'c, 'd)) should be (
      List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }

  "P15" should "Duplicate the elements of a list a given number of times" in {
    s99.duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should be (
      List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)    )
  }

  "P16" should "Drop every Nth element from a list." in {
    s99.drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (
      List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
  }

  "P17" should "Split a list into two parts." in {
    s99.split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (
      (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    )
  }

  "P18" should "Extract a slice from a list" in {
    s99.slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (
      List('d, 'e, 'f, 'g)
    )
  }

  "P19" should "Rotate a list N places to the left" in {
    s99.rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (
      List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
    )
  }

  it should "Rotate inverse on negative index" in {
    s99.rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be (
      List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
    )
  }

  "P20" should "Remove the Kth element from a list" in {
    s99.removeAt(1, List('a, 'b, 'c, 'd)) should be (
      (List('a, 'c, 'd),'b)
    )
  }

  it should "return throw exection when too big index" in {
    intercept[NoSuchElementException] {
      s99.removeAt(6, List('a, 'b, 'c, 'd))
    }
  }

  it should "return throw exection when too low index" in {
    intercept[NoSuchElementException] {
      s99.removeAt(-1, List('a, 'b, 'c, 'd))
    }
  }

  it should "return the first when just asking for first" in {
    s99.removeAt(0, List('a, 'b, 'c, 'd)) should be (
      (List('b, 'c, 'd),'a)
    )
  }






}
