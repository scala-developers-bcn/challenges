package p01_p10

import org.scalatest.FunSpec
import org.scalatest.Matchers

class PalindromeSpec extends FunSpec with Matchers {
  import p01_p10.Palindrome._

  describe("palindrome"){
    describe("when non empty list"){
      it("should return true if the list is a palindrome"){
        isPalindrome(List(1, 2, 3, 2, 1)) should be (true)
      }
      
      it("should return false if the list isn't palindrome"){
        isPalindrome(List(1, 2, 3, 2, 1, 1)) should be (false)
      }
    }

    describe("when a empty list"){
      it("should return true"){
        isPalindrome(List()) should be (true)
      }
    }
  }
}

