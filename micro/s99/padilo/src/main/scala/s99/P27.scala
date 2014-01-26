package s99

import scala.language.postfixOps
/*
 * Group the elements of a set into disjoint subsets.
 * a) In how many ways can a group of 9 people work in 3 disjoint subgroups of 2, 3 and 4 persons? Write a function that generates all the possibilities.
 * Example:
 * 
 * scala> group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
 * res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...
 * b) Generalize the above predicate in a way that we can specify a list of group sizes and the predicate will return a list of groups.
 * 
 * Example:
 * 
 * scala> group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
 * res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...
 * Note that we do not want permutations of the group members; i.e. ((Aldo, Beat), ...) is the same solution as ((Beat, Aldo), ...). However, we make a difference between ((Aldo, Beat), (Carla, David), ...) and ((Carla, David), (Aldo, Beat), ...).
 * 
 * You may find more about this combinatorial problem in a good book on discrete mathematics under the term "multinomial coefficients".
 */
object P27 {
  def group3[T](l: List[T]): List[List[List[T]]] = {
    groupN(l, 3)
  }

  def groupN[T](l: List[T], numGroups: Int): List[List[List[T]]] = {
    group(l, numGroups, 1 to l.length toSet)
  }

  private def group[T](l: List[T], numGroups: Int, groups: Set[Int]): List[List[List[T]]] = {
    def rGroupN[T](l: List[T], numGroups: Int, group: List[List[T]], groups: Set[Int]): List[List[List[T]]] = {
      (numGroups, l) match {
        case (_, head :: tail) => {

          (for {
            i <- groups.toList
            if (i <= l.length - numGroups+1)
            comb <- P26.combinations(i - 1, tail)
          } yield {
            def combWithHead: List[T] = head :: comb
            def newL = tail diff comb

            rGroupN(newL, numGroups - 1, combWithHead :: group, groups)
          }).flatten
        }

        case (0, List()) => List(group.reverse)
        case (_, _) => Nil
      }
    }
    rGroupN(l, numGroups, Nil, groups)
  }

  def group[T](groups: List[Int], list: List[T]): List[List[List[T]]] = {
    require(!groups.exists(_<1), "there a group with members less than 0")
    require(groups.foldLeft(0)(_+_) == list.length, "The sum of items in each group must be the same as the list length")

    group(list, groups.length, groups.toSet)
  }
}