package s99

/*
 * Duplicate the elements of a list a given number of times.
 * Example:
 * scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
 * res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
 */
object p15 {
  def main(args: Array[String]) = {
    println(duplicateN(3, List('a, 'b, 'c, 'c, 'd)))
    println(duplicateN2(3, List('a, 'b, 'c, 'c, 'd)))
    println(duplicateN3(3, List('a, 'b, 'c, 'c, 'd)))
  }

  //recursive
  def duplicateN[T](n: Int, l: List[T]): List[T] = {
    if (l.isEmpty) {
      List()
    } else {
      List.fill(n)(l.head) ::: duplicateN(n, l.tail)
    }
  }

  // tail recursive
  def duplicateN2[T](n: Int, l: List[T]): List[T] = {
    def duplicateNTR[T](n: Int, l: List[T], result: List[T]): List[T] = {
      if (l.isEmpty) {
        result
      } else {
        duplicateNTR(n, l.tail, result:::List.fill(n)(l.head))
      }
    }
    duplicateNTR(n, l, List())
  }
  
  // flatmap
  def duplicateN3[T](n: Int, l: List[T]): List[T] = {
    l.flatMap{
      List.fill(n)(_)
    }
  }
  

}