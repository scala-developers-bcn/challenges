package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class CompressSpec extends FunSpec with Matchers {
  import p01_p10.Compress._

  describe("compress"){
    describe("when no empty list with consecutive duplicates"){
      it("should return a list without duplicates"){
        compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List('a, 'b, 'c, 'a, 'd, 'e))
      }
    }
  }
}

