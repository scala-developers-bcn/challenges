package p01_p10

object Compress {
  def compress[A](list: List[A]): List[A] = list match {
    case Nil => Nil
    case head :: Nil => list
    case head :: tail => 
      if (head == tail.head) compress(tail)
      else head :: compress(tail)
  }
}
