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

  // I suspect this can be tailrec but still havent wrapped my head around it...
  def isPalindrome[T](xs: List[T]) : Boolean = {
    xs match {
      case Nil => true
      case x :: Nil => true
      case h :: tail => xs.head == last(xs) && isPalindrome(xs.tail.take(xs.tail.length-1))
    }
  }

  // this solution I don't like,
  def flatten(xss: List[Any]) : List[Any] = xss.foldLeft(List[Any]())((acc, x) => 
     x match {
        case l : List[_] => flatten(l).reverse ::: acc
        case _ => x :: acc
     }).reverse
  
  def compress[T](xs: List[T]) : List[T] = xs.foldLeft(List[T]())( 
    (acc, x) => 
       if (acc.isEmpty || x!=acc.head) x :: acc else acc
   ).reverse

  def pack[T](xs: List[T]) : List[List[T]] = {
    @scala.annotation.tailrec
    def pack0(result: List[List[T]], acc: List[T], xs: List[T]):List[List[T]] = {
      (acc, xs) match {
       case (_, Nil)                       => (acc :: result).reverse
       case (Nil, _)                       => pack0(result, List(xs.head), xs.tail)
       case (_, h :: t) if (acc.head == h) => pack0(result, h :: acc, t)
       case _                              => pack0(acc :: result, List(xs.head), xs.tail)
      }
    }
    pack0(Nil, Nil, xs)
  }

  def encode[T](xs: List[T]): List[(Int, T)] = pack(xs) map { l => (l.length, l.head) }

}
