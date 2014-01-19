package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class LengthSpec extends FunSpec with Matchers {
  import p01_p10.Length._

  describe("length"){
    describe("when non empty list"){
      it("should return the number of elements of the list"){
        Length.length(List(1, 1, 2, 3, 5, 8)) should be (6)
      }
    }

    describe("when a empty list"){
      it("should return 0"){
        Length.length(List()) should be (0)
      }
    }
  }
}

