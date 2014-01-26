package s99

import scala.annotation.tailrec
import scala.collection.parallel.immutable.ParMap
/*
 * Sorting a list of lists according to length of sublists.
 * a) We suppose that a list contains elements that are lists themselves. The objective is to sort the elements of the list according to their length. E.g. short lists first, longer lists later, or vice versa.
 * Example:
 * 
 * scala> lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
 * res0: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
 * b) Again, we suppose that a list contains elements that are lists themselves. But this time the objective is to sort the elements according to their length frequency; i.e. in the default, sorting is done ascendingly, lists with rare lengths are placed, others with a more frequent length come later.
 * 
 * Example:
 * 
 * scala> lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
 * res1: List[List[Symbol]] = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))
 * Note that in the above example, the first two lists in the result have length 4 and 1 and both lengths appear just once. The third and fourth lists have length 3 and there are two list of this length. Finally, the last three lists have length 2. This is the most frequent length.
 */

object P28 {
  import scala.language.implicitConversions

  // Just to be sure this doesn't affect to other problem solutions, I'm not sure if It's necessary but I feel better with private :)
  private implicit def listToMyRichList[T](l: List[T]) = new ComparableList(l)

  private class ComparableList[T](cList: List[T]) {
    private def list = cList

    def <(that: ComparableList[T]): Boolean = {
      @tailrec
      def isLess(l1: List[T], l2: List[T]): Boolean = {
        (l1, l2) match {
          case (h1 :: t1, h2 :: t2) => {
            val sh1 = h1.toString
            val sh2 = h2.toString

            if (sh1 < sh2) true
            else if (sh1 > sh2) false
            else isLess(t1, t2)
          }
          // this cases don't affect to the solution but it makes the solution valid for others
          case (Nil, _) => {
            false
          }
          case (_, Nil) => {
            true
          }
        }
      }

      def l1 = list
      def l2 = that.list

      isLess(l1, l2)
    }
  }

  def lsort(list: List[List[Symbol]]): List[List[Symbol]] = list.sortWith((l1, l2) => {
    l1.length < l2.length || (l1.length == l2.length && l1 < l2)
  })

  def lsortFreq(list: List[List[Symbol]]): List[List[Symbol]] = {
    // this generates the Map[subListLength, frequency]
    def len2Freq = list.foldLeft(Map[Int, Int]()) {
      (map, elem) =>
        {
          val len = elem.length
          map.get(len) match {
            case Some(freq) => {
              map + (len -> (freq + 1))
            }
            case None => {
              map + (len -> 1)
            }
          }
        }
    }

    list.sortWith((l1, l2) => {
      val freq1 = len2Freq(l1.length)
      val freq2 = len2Freq(l2.length)

      freq1 < freq2 || (freq1 == freq2 && l1 < l2)
    })
  }
}