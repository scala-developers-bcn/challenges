package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class NthSpec extends FunSpec with Matchers {
  import p01_p10.Nth._

  describe("nth"){
    describe("when list with at least nth element in the list"){
      it("should return the nth element of the list"){
        nth(0, List(1)) should be (1)
        nth(2, List(1, 1, 2, 3, 5, 8)) should be (2)
      }
    }

    describe("when nth element doesn't exists"){
      it("should produce NoSouchElementException"){
        evaluating(nth(2, List(1, 1))) should produce [NoSuchElementException]
        evaluating(nth(2, List())) should produce [NoSuchElementException]
      }
    }
  }
}
