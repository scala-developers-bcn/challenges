package s99

/*
 *  Eliminate consecutive duplicates of list elements.
 *  If a list contains repeated elements they should be replaced with a single copy of the element. The order of the elements should not be changed.
 *  Example:
 *  
 *  scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
 *  res0: List[Symbol] = List('a, 'b, 'c, 'a, 'd, 'e)
 */
object P08 {
  def main(args: Array[String]): Unit = {
    val input = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)

    println(compress(input))
    println(compress2(input))
    println(compress3(input))
  }

  // This method generates the solution reversed, so a reverse is needed to generate the correct answer

  def compress[T](l: List[T]): List[T] = {
    def compressTR(l: List[T], result: List[T]): List[T] = {
      l match {
        case List() => result
        case head :: tail => compressTR(tail.dropWhile((_ == head)), head :: result)
      }
    }

    compressTR(l, List()).reverse
  }

  def compress2[T](l: List[T]): List[T] = {
    def compressTR(l: List[T], result: List[T]): List[T] = {
      l match {
        case head :: List() => head :: result
        case head :: tail if (head != tail.head) => compressTR(tail, head :: result)
        case head :: tail => compressTR(tail, result)
        case _ => result
      }
    }

    compressTR(l, List()).reverse
  }

  // using foldRight
  def compress3[T](l: List[T]): List[T] = {
    def compare(head: T, tail: List[T]): List[T] = {
      if (!tail.isEmpty && head == tail.head) tail
      else head :: tail
    }
    l.foldRight(List[T]())(compare)
  }

}
  