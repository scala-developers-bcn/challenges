package p01_p10

object Palindrome {
  import Reverse._
  def isPalindrome[A](list: List[A]): Boolean = list == reverse(list)
}

