package s99

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

/**
 * Created with IntelliJ IDEA.
 * User: jaranda
 * Date: 05/01/14
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */

class ListsSpec extends FlatSpec with ShouldMatchers {

  "Lists problems set" should "find the last element of a list" in {
    Lists.last(List(1, 1, 2, 3, 5, 8)) should be (8)
  }

  it should "find the last but one element of a list" in {
    Lists.penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
  }

  it should "find the kth element of a list" in {
    Lists.nth(2, List(1, 1, 2, 3, 5, 8)) should be (2)
  }

  it should "find the number of elements of a list" in {
    Lists.length(List(1, 1, 2, 3, 5, 8)) should be (6)
  }

  it should "reverse a list"  in {
    Lists.reverse(List(1, 1, 2, 3, 5, 8)) should be (List(8, 5, 3, 2, 1, 1))
  }

  it should "check whether a given list is palindrome or not" in {
    Lists.isPalindrome(List(1, 2, 3, 2, 1)) should be (true)
    Lists.isPalindrome(List(1,2,3)) should be (false)
  }

  it should "flatten a nested list structure" in {
    Lists.flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be (List(1, 1, 2, 3, 5, 8))
  }

  it should "eliminate consecutive duplicates of list elements" in {
    Lists.compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be (
      List('a, 'b, 'c, 'a, 'd, 'e)
    )
  }

  it should "pack consecutive duplicates of list elements into sublists" in {
    Lists.pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(
      List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
    )
  }

  it should "encode list elements" in {
    Lists.encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(
      List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)))
  }

}
