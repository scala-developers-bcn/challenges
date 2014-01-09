package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class EncodeSpec extends FunSpec with Matchers {
  import p01_p10.Encode._

  describe("encode"){
    describe("when no empty list with consecutive duplicates"){
      it("should return a list with pairs of number of consecutives and element"){
        encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
      }
    }
  }
}

