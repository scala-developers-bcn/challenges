package s99

import scala.annotation.tailrec

/*
 * Generate the combinations of K distinct objects chosen from the N elements of a list.
 * In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
 * Example:
 * 
 * scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
 * res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
*/
object P26 {
  def combinations[T](n: Int, l: List[T]): List[List[T]] = {

    def rCombinations(i: Int, l: List[T], comb: List[T], result: List[List[T]]): List[List[T]] = {
      if (i == 0) {
        comb.reverse :: result
      } else {
        l match {
          case head :: tail => {
            rCombinations(i - 1, tail, head :: comb, rCombinations(i, tail, comb, result))
          }
          case _ => {
            result
          }
        }
      }
    }
    rCombinations(n, l, Nil, Nil)
  }

}