package p01_p10

object Penultimate {
  def penultimate[A](list: List[A]): A = list match {
    case Nil => throw new NoSuchElementException
    case elem :: Nil => throw new NoSuchElementException
    case elem :: tail => tail match {
      case elem1 :: Nil => elem
      case elem1 :: tail1 => penultimate(tail)
    }
  }
}
