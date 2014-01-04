package com.agilogy

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FakeSuite extends FunSuite {

  test("Fake#something is true") {
    assert(new Fake().something === true)
  }

}