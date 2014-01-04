package s99

object Lists {

  def last[T](xs: List[T]) : T = xs.reverse.head

  def penultimate[T](xs:List[T]) : T = xs.reverse.tail.head

  @scala.annotation.tailrec 
  def nth[T](index: Int, xs:List[T]) : T = index match {
    case 0 => xs.head
    case _ => nth(index-1, xs.tail)
  } 

  def length[T](xs: List[T] ) : Int = xs.foldLeft(0)((acc,x) => acc+1)

  def reverse[T](xs: List[T] ) : List[T] = xs.foldLeft(List[T]())( (acc,x) => x :: acc  )

  def isPalindrome[T](xs: List[T]) : Boolean = {
    xs match {
      case Nil => true
      case x :: Nil => true
      case h :: tail => xs.head == last(xs) && isPalindrome(xs.tail.take(xs.tail.length-1))
    }
  }

}
