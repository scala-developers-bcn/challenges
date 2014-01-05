package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class FlattenSpec extends FunSpec with Matchers {
  import p01_p10.Flatten._

  describe("flatten"){
    describe("when non empty list"){
      it("should return a flatten list"){
        flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
      }
    }

    describe("when empty list"){
      it("should return a empty list"){
        flatten(List()) should be (List())
      }
    }

    describe("when one element list"){
      it("should return one element list"){
        flatten(List(List(List(1)))) should be (List(1))
      }
    }
  }
}

