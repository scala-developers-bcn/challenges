package com.agilogy

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class FakeFlatSpec extends FlatSpec with ShouldMatchers {

  "A Fake" should "return true when required to do something" in {
    new Fake().something should be (true)
  }

}