package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class ReverseSpec extends FunSpec with Matchers {
  import p01_p10.Reverse._

  describe("reverse"){
    describe("when non empty list"){
      it("should return the reverse list"){
        reverse(List(1, 1, 2, 3, 5, 8)) should be (List(8, 5, 3, 2, 1, 1))
      }
    }

    describe("when a empty list"){
      it("should return empty list"){
        reverse(List()) should be (List())
      }
    }
  }
}

