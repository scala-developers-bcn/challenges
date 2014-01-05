package org.scalabcn.s99

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalabcn.s99.Week1._

@RunWith(classOf[JUnitRunner])
class Week1FlatSpec extends FlatSpec with ShouldMatchers {

  "P01" should "return the last element of a list" in {
    last(List(1, 1, 2, 3, 5, 8)) should be(8)
  }

  it should "throw IllegalArgumentException when list is empty" in {
    intercept[IllegalArgumentException] {
      last(List())
    }
  }

  "P02" should "return the last but one element of a list" in {
    penultimate(List(1, 1, 2, 3, 5, 8)) should be(5)
  }

  it should "throw IllegalArgumentException when the list is not long enough" in {
    intercept[IllegalArgumentException] {
      penultimate(List(1))
    }
  }

  "P03" should "return the nth element of a list" in {
    nth(2, List(1, 1, 2, 3, 5, 8)) should be(2)
  }

  it should "throw IndexOutOfBoundsException when the list is not long enough" in {
    intercept[IndexOutOfBoundsException] {
      nth(10, List(1, 1, 2, 3, 5, 8))
    }
  }

  "P04" should "return the number of elements of a list" in {
    //TODO: Check how to avoid import
    Week1.length(List(1, 1, 2, 3, 5, 8)) should be(6)
  }

  "P05" should "return the list in reverse order" in {
    reverse(List(1, 1, 2, 3, 5, 8)) should be(List(8, 5, 3, 2, 1, 1))
  }

  it should "return the empty list when reversing another empty list" in {
    reverse(List()) should be(List())
  }

  "P06" should "return true when a list is a palindrome" in {
    isPalindrome(List(1, 2, 3, 2, 1)) should be(true)
  }

  it should "return true since an empty list is a palindrome" in {
    isPalindrome(List()) should be(true)
  }

  it should "return false when a list is not a palindrome" in {
    isPalindrome(List(1, 2, 3, 2, 2)) should be(false)
  }

  "P07" should "return the flatten list" in {
    flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be(List(1, 1, 2, 3, 5, 8))
  }

  it should "return the empty list when no elements" in {
    flatten(List()) should be(List())
  }

  "P08" should "eliminate duplicates from a list" in {
    compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List('a, 'b, 'c, 'a, 'd, 'e))
  }

  it should "return the empty list when no elements" in {
    compress(List()) should be(List())
  }

  "P09" should "pack same elements in a row in sublists" in {
    pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
  }

  it should "return the empty list when no elements" in {
    pack(List()) should be(List())
  }

  "P10" should "encode a list of elements using a list of amount-symbol sublists" in {
    encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  }

  it should "return the empty list when no elements" in {
    pack(List()) should be(List())
  }

}
