package com.agilogy;

import org.specs2.mutable.Specification;

class FakeSpecs2Spec extends Specification {

  "A Fake" should {
    "return true when something is invoked" in {
      new Fake().something should equalTo(true)
    }
  }

}
