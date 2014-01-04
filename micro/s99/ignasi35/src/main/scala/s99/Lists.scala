package s99

object Lists {

  def last[T](in: List[T]) : T = in.reverse.head

  def penultimate[T](in:List[T]) : T = in.reverse.tail.head

  def nth[T](index: Int, xs:List[T]) : T = index match {
    case 0 => xs.head
    case _ => nth(index-1, xs.tail)
  } 

  def length[T](xs: List[T] ) : Int = xs.foldLeft(0)((acc,x) => acc+1)

}
