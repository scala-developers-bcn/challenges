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

}
