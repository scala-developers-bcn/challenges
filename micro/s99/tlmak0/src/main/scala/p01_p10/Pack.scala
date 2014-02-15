package p01_p10

object Pack {
  def pack[A](list: List[A]): List[List[A]] = list match{
    case Nil => List(List())
    case head :: tail => {
      val (consecutives, others) = list span { _ == head }
      if (others == Nil) List(consecutives)
      else consecutives :: pack(others)
    }
  }
}
