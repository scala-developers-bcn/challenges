package s99


import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class ListsSpec extends FlatSpec with ShouldMatchers {

  import s99.Lists._

  "A s99 problem set on lists" should "find the last element of a list" in {
    last(List(1, 1, 2, 3, 5, 8)) should be(8)
  }

  it should "throw IllegalArgumentException when asked the last element of empty list" in {
    intercept[IllegalArgumentException] {
      last(List[Int]())

    }
  }

  it should " Find the last but one element of a list." in {
    penultimate(List(1, 1, 2, 3, 5, 8)) should be(5)
  }

  it should "throw IllegalArgumentException when asked the penultimate element of lists shorter (or equal) than 1" in {
    intercept[IllegalArgumentException] {
      penultimate(List[Int]())
      penultimate(List(1))
    }
  }

  it should " Find the Kth element of a list.  (By convention, the first element in the list is element 0.)" in {
    nth(2, List(1, 1, 2, 3, 5, 8)) should be(2)
  }

  it should " Find the number of elements of a list." in {
    // #length seems to be a publicly available method and so even though I import Lists._ this other #length() is used instead causing a compilation error. Current solution I found was to add Lists. to the invotacion to ensure my Lists#length isbeing used.
    Lists.length(List(1, 1, 2, 3, 5, 8)) should be(6)
  }

  it should "Reverse a list." in {
    reverse(List(1, 1, 2, 3, 5, 8)) should be(List(8, 5, 3, 2, 1, 1))
  }

  it should "Find out whether a list is a palindrome." in {
    isPalindrome(List(1, 2, 3, 2, 1)) should be(true)
  }

  it should "Not find a palindrome when there's none" in {
    isPalindrome(List(1, 2, 3, 3, 1)) should be(false)
  }

  it should "Flatten a nested list structure." in {
    flatten(List(List(1, 1), 2, List(3, List(5, 8)))) should be(List(1, 1, 2, 3, 5, 8))
  }

  it should "Eliminate consecutive duplicates of list elements. If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed." in {
    compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List('a, 'b, 'c, 'a, 'd, 'e))
  }

  it should "Pack consecutive duplicates of list elements into sublists. If a list contains repeated elements they should be placed in separate sublists." in {
    pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(
      List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
    )
  }

  it should "Run-length encoding of a list.  Use the result of problem P09 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E." in {
    encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(
      List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  }

  it should "Modified run-length encoding. Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as (N, E) terms." in {
    encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(
      List((4, 'a), 'b, (2, 'c), (2, 'a), 'd, (4, 'e)))
  }

  it should "Decode a run-length encoded list. Given a run-length code list generated as specified in problem P10, construct its uncompressed version." in {
    decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))) should be(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
  }

  it should "Run-length encoding of a list (direct solution). Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly." in {
    encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)) should be(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
  }

  /*
  it should "Duplicate the elements of a list." in {
    duplicate(List('a, 'b, 'c, 'c, 'd)) should be(List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd))
  }


P15 (**) Duplicate the elements of a list a given number of times.
Example:
scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
P16 (**) Drop every Nth element from a list.
Example:
scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
P17 (*) Split a list into two parts.
The length of the first part is given. Use a Tuple for your result.
Example:

scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
P18 (**) Extract a slice from a list.
Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. Start counting the elements with 0.
Example:

scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res0: List[Symbol] = List('d, 'e, 'f, 'g)
P19 (**) Rotate a list N places to the left.
Examples:
scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)

scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
P20 (*) Remove the Kth element from a list.
Return the list and the removed element in a Tuple. Elements are numbered from 0.
Example:

scala> removeAt(1, List('a, 'b, 'c, 'd))
res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)

   */


}
