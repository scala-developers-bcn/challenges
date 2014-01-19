package s99

/*
 * Find the last but one element of a list.
 * Example:
 * scala> penultimate(List(1, 1, 2, 3, 5, 8))
 * res0: Int = 5
 */
object p02 {
  def main(args: Array[String]): Unit = {
    println(penultimate(List(1, 1, 2, 3, 5, 8)))
  }

  def penultimate(l: List[Int]): Int = l match {
    case head1 :: head2 :: List() => head1
    case head :: tail => penultimate(tail)
    case _ => throw new NoSuchElementException
  }

}