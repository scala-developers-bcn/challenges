package org.scalabcn.s99

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalabcn.s99.Week1._
import org.scalabcn.s99.Week2._

@RunWith(classOf[JUnitRunner])
class Week2FlatSpec extends FlatSpec with ShouldMatchers {

  "P11" should "return the encoded string as amount-char pairs only when amount is greater than one" in {
    encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e)))
  }

  "P12" should "return the decoded string from an amount-char pair list" in {
    decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) should be(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  }

  "P13" should "return the encoded string as amount-char pairs without dependencies" in {
    encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

  "P14" should "return a new list with each element duplicated" in {
    duplicate(List('a, 'b, 'c, 'c, 'd)) should be(List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }

  "P15" should "return a new list with each element repeated N times" in {
    duplicateN(3, List('a, 'b, 'c, 'c, 'd)) should be(List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd))
  }

  "P16" should "drop every Nth element of the list" in {
    drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be(List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k))
  }

  it should "throw IllegalArgumentException when the index is below 1" in {
    intercept[IllegalArgumentException] {
      drop(0, List('a))
    }
  }

  it should "drop every element when the index is 1" in {
    drop(1, List('a, 'b, 'c)) should be(List())
  }

  "P17" should "split a list into two parts given the length of the first part" in {
    split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be((List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))
  }

  it should "throw IllegalArgumentException when the length is below 0" in {
    intercept[IllegalArgumentException] {
      split(-1, List('a, 'b))
    }
  }

  it should "throw IllegalArgumentException when the length is greater than the list's length" in {
    intercept[IllegalArgumentException] {
      split(10, List('a, 'b))
    }
  }

  it should "return the whole list as the second element when the length is 0" in {
    split(0, List('a, 'b)) should be((Nil, List('a, 'b)))
  }

  "P18" should "return the elements from the first index until the second index" in {
    slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be(List('d, 'e, 'f, 'g))
  }

  "P19" should "rotate a list n places to the left" in {
    rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be(List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c))
  }

  it should "rotate to the right when negative" in {
    rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)) should be(List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i))
  }

  it should "rotate when the number of places is bigger than the length" in {
    rotate(3, List('a, 'b)) should be(List('b, 'a))
  }

  it should "rotate to the right when the number of places is bigger than the length" in {
    rotate(-3, List('a, 'b)) should be(List('b, 'a))
  }

  "P20" should "remove the nth element from a list" in {
    removeAt(1, List('a, 'b, 'c, 'd)) should be((List('a, 'c, 'd),'b))
  }

  it should "remove the first element from a list" in {
    removeAt(0, List('a, 'b, 'c)) should be((List('b, 'c), 'a))
  }

  it should "remove the last element from a list" in {
    removeAt(2, List('a, 'b, 'c)) should be((List('a, 'b), 'c))  
  }

  it should "raise a IllegalArgumentException when the index is out of bounds" in {
    intercept[IllegalArgumentException]{
      removeAt(5, List('a, 'b))
    }
    intercept[IllegalArgumentException]{
      removeAt(-1, List('a))
    }
  }
}
