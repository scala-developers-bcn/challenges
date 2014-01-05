package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class LastSpec extends FunSpec with Matchers {
  import p01_p10.Last._

  describe("last"){
    describe("when non empty list"){
      it("should return the last element of the list"){
        last(List(1, 1, 2, 3, 5, 8)) should be(8)
      }
    }

    describe("when empty list"){
      it("should produce NoSuchElementException"){
        evaluating(last(Nil)) should produce [NoSuchElementException]
      }
    }
  }
}
