package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class PackSpec extends FunSpec with Matchers {
  import p01_p10.Pack._

  describe("pack"){
    describe("when no empty list with consecutive duplicates"){
      it("should return a list with separate sublists"){
        pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)))
      }
    }
  }
}

