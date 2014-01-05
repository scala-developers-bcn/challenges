package p01_p10

object Reverse {
  def reverse[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case elem :: Nil => List(elem)
    case elem :: tail => reverse(tail) ::: List(elem)
  }
}

