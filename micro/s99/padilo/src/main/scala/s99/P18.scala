package s99

/*
 * Extract a slice from a list.
 * Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. Start counting the elements with 0.
 * Example:
 * 
 * scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
 * res0: List[Symbol] = List('d, 'e, 'f, 'g) 
 */
object P18 {
  def main(args: Array[String]) = {
    val sample1 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val sample2 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val sample3 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val sample4 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    val sample5 = List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)
    
    println("SLICE") 
    println(slice(3, 7, sample1))
    println(slice(2, 131231, sample2))
    println(slice(23413, 1, sample3))
    println(slice(-1, 3, sample4))
    println(slice(3, -3, sample5))

    println("\n")
    println("SLICE2")
    println(slice(3, 7, sample1))
    println(slice(2, 131231, sample2))
    println(slice(23413, 1, sample3))
    println(slice(-1, 3, sample4))
    println(slice(3, -3, sample5))

    println("\n")
    println("SLICE3")
    println(slice(3, 7, sample1))
    println(slice(2, 131231, sample2))
    println(slice(23413, 1, sample3))
    println(slice(-1, 3, sample4))
    println(slice(3, -3, sample5))

    println("\n")
    println("SLICE4")
    println(slice(3, 7, sample1))
    println(slice(2, 131231, sample2))
    println(slice(23413, 1, sample3))
    println(slice(-1, 3, sample4))
    println(slice(3, -3, sample5))
  }

  // Recursive
  def slice[T](i: Int, k: Int, l: List[T]): List[T] = {
    (i, k, l) match {
      case (_, _, List()) => List()
      case (_, k, _) if (k <= 0) => List()
      case (i, k, head :: tail) if (i <= 0) => head :: slice(i, k - 1, tail)
      case (i, k, _ :: tail) => slice(i - 1, k - 1, tail)
    }
  }

  // Tail recursive
  def slice2[T](i: Int, k: Int, l: List[T]): List[T] = {
    def sliceTR[T](i: Int, k: Int, l: List[T], result: List[T]): List[T] = {
      (i, k, l) match {
        case (_, _, List()) => result
        case (_, k, _) if (k <= 0) => result
        case (i, k, head :: tail) if (i <= 0) => sliceTR(i, k - 1, tail, head :: result)
        case (i, k, _ :: tail) => sliceTR(i - 1, k - 1, tail, result)
      }
    }

    sliceTR(i, k, l, List()).reverse
  }

  // Easy solution
  def slice3[T](i: Int, k: Int, l: List[T]): List[T] = {
    l.drop(i).take(k - (i max 0))
  }

  // Funny very inefficient solution, inspired by the one provided by Phil for P16
  def slice4[T](i: Int, k: Int, l: List[T]): List[T] = {
    l.zipWithIndex filter { v => v._2 >= i && v._2 < k } map (v => v._1)

  }

}