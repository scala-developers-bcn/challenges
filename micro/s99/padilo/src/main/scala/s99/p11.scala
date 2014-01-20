package s99

/*
 * Modified run-length encoding.
 * Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. Only elements with duplicates are transferred as (N, E) terms.
 * Example:
 * 
 * scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
 * res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
 */
object p11 {

  def main(args: Array[String]): Unit = {
    println(encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }

  def encodeModified[T](l: List[T]) = {
    p10.encode(l).map {
      case (1, s) => s
      case p => p
    }
  }

  // Just for fun, here's a more typesafe version. (provided by Phil Gold)
  def encodeModified2[A](ls: List[A]): List[Either[A, (Int, A)]] = {
    p10.encode(ls) map { t => if (t._1 == 1) Left(t._2) else Right(t)
    }
  }
}