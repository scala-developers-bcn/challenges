package s99

/*
 * Rotate a list N places to the left.
 * Examples:
 * scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 * res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
 * 
 * scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 * res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
 */
object p19 {
  def main(args: Array[String]) = {
    test("rotate", rotate)
    test("rotate2", rotate2)
    test("rotate3", rotate3)
  }
  

  def test[T](name: String, f: (Int, List[Symbol]) => List[T]) = {
    val l = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)

    println(name)
    println(f(3, l))
    println(f(-2, l))
    println(f(0, l))
    println(f(133, l))
    println(f(-321, l))
    println(f(12, List()))
    println
  }

  // tail recursive
  def rotate[T](n: Int, l: List[T]): List[T] = {
    def rotateTR[T](n: Int, l: List[T], result: List[T]): List[T] = {
      (n, l) match {
        case (0, l) => l ::: result.reverse
        case (n, h :: t) if n > 0 => rotateTR(n - 1, t, h :: result)
        case _ => result.reverse
      }
    }
    val i = if (n < 0) {
      l.length + n max 0
    } else {
      n
    }
    rotateTR (i, l, List())
  }

  // using split
  def rotate2[T](n: Int, l: List[T]) = {
    val i = if (n < 0) {
      l.length + n max 0
    } else {
      n  
    }
    def split = l.splitAt(i)
    split._2 ::: split._1
  }

  // using drop+take
  def rotate3[T](n: Int, l: List[T]) = {
    val i = if (n < 0) {
      l.length + n max 0
    } else {
      n
    }
    l.drop(i) ::: l.take(i)
  }
}