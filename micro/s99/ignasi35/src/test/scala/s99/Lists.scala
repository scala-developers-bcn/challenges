package s99


import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec

class ListsSpec extends FlatSpec with ShouldMatchers {

 import s99.Lists._

 "A s99 problem set on lists" should "find the last element of a list" in {
   last(List(1, 1, 2, 3, 5, 8)) should be(8)
 }

 it should " Find the last but one element of a list." in {
    penultimate(List(1, 1, 2, 3, 5, 8)) should be (5)
 }

 it should " Find the Kth element of a list.  (By convention, the first element in the list is element 0.)" in { 
    nth(2, List(1, 1, 2, 3, 5, 8)) should be(2)
 }

 it should " Find the number of elements of a list." in {
  // #length seems to be a publicly available method and so even though I import Lists._ this other #length() is used instead causing a compilation error. Current solution I found was to add Lists. to the invotacion to ensure my Lists#length isbeing used.
   Lists.length(List(1, 1, 2, 3, 5, 8)) should be(6)
 }

   /*
P05 (*) Reverse a list.
Example:
scala> reverse(List(1, 1, 2, 3, 5, 8))
res0: List[Int] = List(8, 5, 3, 2, 1, 1)
P06 (*) Find out whether a list is a palindrome.
Example:
scala> isPalindrome(List(1, 2, 3, 2, 1))
res0: Boolean = true
P07 (**) Flatten a nested list structure.
Example:
scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
res0: List[Any] = List(1, 1, 2, 3, 5, 8)
P08 (**) Eliminate consecutive duplicates of list elements.
If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
Example:

scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
P09 (**) Pack consecutive duplicates of list elements into sublists.
If a list contains repeated elements they should be placed in separate sublists.
Example:

scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
P10 (*) Run-length encoding of a list.
Use the result of problem P09 to implement the so-called run-length encoding data compression method. Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E.
Example:

scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))

*/

}
