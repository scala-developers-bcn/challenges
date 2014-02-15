package s99

object P05 {
  /*
   * Reverse a list.
   * Example:
   * scala> reverse(List(1, 1, 2, 3, 5, 8))
   * res0: List[Int] = List(8, 5, 3, 2, 1, 1)
   */
  def main(args: Array[String]):Unit = {
    println(reverse3(List(1, 1, 2, 3, 5, 8)))
  }
  
  def reverse[T](l:List[T]) ={
    l.reverse
  }

  def reverse2[T](l:List[T]):List[T] ={
    l match {
      case head::tail => reverse2(tail):::List(head)
      case _ => List()
    }
  }

  def reverse3[T](l:List[T]):List[T] ={
    def reverse3TR(l:List[T], result:List[T]):List[T] ={
      l match {
        case head::tail => reverse3TR(tail, head::result)
        case _ => result
      }
    }
    reverse3TR(l, List())
  }

}