package p01_p10

object Last{
  def last[A](list: List[A]): A = list match {
    case elem :: Nil => elem
    case elem :: tail => last(tail)
    case Nil => throw new NoSuchElementException
  }
}
