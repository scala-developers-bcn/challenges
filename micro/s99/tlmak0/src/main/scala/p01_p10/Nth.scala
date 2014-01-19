package p01_p10

object Nth {
  def nth[A](pos: Int, list: List[A]): A = list match {
    case Nil => throw new NoSuchElementException
    case elem :: tail => 
      if (pos == 0) elem
      else nth(pos - 1, tail)
  }
}
