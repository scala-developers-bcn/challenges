package s99

/*
 * Duplicate the elements of a list.
 * Example:
 * scala> duplicate(List('a, 'b, 'c, 'c, 'd))
 * res0: List[Symbol] = List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd)
 */
object p14 {
  def main(args: Array[String]) = {
    println(duplicate(List('a, 'b, 'c, 'c, 'd)))
    println(duplicate2(List('a, 'b, 'c, 'c, 'd)))
    println(duplicate3(List('a, 'b, 'c, 'c, 'd)))
  }

  // recursive
  def duplicate[T](l: List[T]): List[T] = {
    if (l.isEmpty) {
      List()
    } else {
      l.head :: l.head :: duplicate(l.tail)
    }

  }

  // tail recursive
  def duplicate2[T](l: List[T]): List[T] = {
    def duplicateTR[T](l: List[T], result: List[T]): List[T] = {
      if (l.isEmpty) {
        result
      } else {
        duplicateTR(l.tail, l.head :: l.head :: result)
      }
    }
    duplicateTR(l, List())
  }

  // provided by Phil
  def duplicate3[A](ls: List[A]): List[A] = ls flatMap { e => List(e, e) }
}