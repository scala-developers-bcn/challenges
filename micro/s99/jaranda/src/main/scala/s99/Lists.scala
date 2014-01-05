package s99

import scala.annotation.tailrec
import sun.font.TrueTypeFont

/**
 * Created with IntelliJ IDEA.
 * User: jaranda
 * Date: 05/01/14
 * Time: 09:47
 * To change this template use File | Settings | File Templates.
 */

object Lists {

  /**
   * Find the last element of a list.
   * @param xs The provided list
   * @tparam T The type of the list
   * @return The last element of the list, if exists. It throws NoSuchElementException otherwise.
   */
  def last[T](xs: List[T]) : T = xs match {
    case x :: Nil => x
    case head :: tail => last(tail)
    case _ => throw new NoSuchElementException
  }

  /**
   * Find the last but one element of a list.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The penultimate element of the list, if exists. It throws NoSuchElementException otherwise.
   */
  def penultimate[T](xs: List[T]) : T = xs match {
    case x :: y :: Nil => x
    case head :: tail => penultimate(tail)
    case _ => throw new NoSuchElementException
  }

  /**
   * Find the Kth element of a list.
   * By convention, the first element in the list is element 0.
   * @param n The index of the element to retrieve.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The nth element of the list, if exists.
   */
  @tailrec
  final def nth[T](n: Int, xs: List[T]) : T = n match {
    case 0 => xs.head
    case _ => nth(n-1, xs.tail)
  }

  /**
   * Find the number of elements of a list.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The length of the list.
   */
  def length[T](xs: List[T]) : Int = xs.foldLeft(0)((acc,x) => acc + 1)

  /**
   * Reverses a list
   * @param xs The provided list to reverse.
   * @tparam T The type of the list.
   * @return The reversed list.
   */
  def reverse[T](xs: List[T]) : List[T] = xs.foldLeft(List[T]())((acc,x) => x :: acc)

  /**
   * Find out whether a list is palindrome
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return True if the list is palindrome, false otherwise.
   */
  def isPalindrome[T](xs: List[T]) : Boolean = xs == reverse(xs) //Not efficient, I know...

  /**
   * Flatten a nested list structure.
   * @param xs The provided list to flat.
   * @return The flatted list.
   */
  //TODO: Implement own version, this one was provided by ignasi35 as a possible option.
  def flatten(xs: List[Any]) : List[Any] = xs.foldLeft(List[Any]())( (acc, x) =>
    x match {
      case l : List[_] => flatten(l).reverse ::: acc
      case _ => x :: acc
    }).reverse

  /**
   * Eliminate consecutive duplicates of list elements.
   * If a list contains repeated elements they should be replaced with a single copy of the element.
   * The order of the elements should not be changed.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The compressed list.
   */
  //TODO: tail-recursive version?
  def compress[T](xs: List[T]) : List[T] = xs match {
    case Nil => Nil
    case head :: tail => head :: compress(tail.dropWhile(x => x == head))
  }

  /**
   * Pack consecutive duplicates of list elements into sublists.
   * If a list contains repeated elements they should be placed in separate sublists.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The packed sublists.
   */
  //TODO: tail-recursive version?
  def pack[T](xs: List[T]) : List[List[T]] = xs match {
    case Nil => Nil
    case head :: tail =>
      val (first, rest) = xs.span(x => x == head)
      first :: pack(rest)
  }

  /**
   * Run-length encoding of a list.
   * Use the result of problem P09 to implement the so-called run-length encoding data compression method.
   * Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E.
   * @param xs The provided list.
   * @tparam T The type of the list.
   * @return The encoded list.
   */
  def encode[T](xs: List[T]) : List[(Int,T)] = {
    pack(xs).map(x => (x.length, x.head))
  }

}
