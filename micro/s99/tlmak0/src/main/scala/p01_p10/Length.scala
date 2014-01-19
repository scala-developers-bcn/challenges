package p01_p10

object Length {
  def length[A](list: List[A]): Int = list match {
    case Nil => 0
    case elem :: tail => 1 + length(tail)
  }
}

