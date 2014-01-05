package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class PenultimateSpec extends FunSpec with Matchers{
  import p01_p10.Penultimate._

  describe("penultimate"){
    describe("when non empty list"){
      it("should return the penultimate element of the list"){
        penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
      }
    }

    describe("when a empty list"){
      it("should produce NoSuchElementException"){
        evaluating(penultimate(List())) should produce [NoSuchElementException]
      }
    }

    describe("when only one element in the list"){
      it("should produce NoSuchElementException"){
        evaluating(penultimate(List(1))) should produce [NoSuchElementException]
      }
    }
  }
}
