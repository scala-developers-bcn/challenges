package s99

object P20 {

  /*
   * Remove the Kth element from a list.
   * Return the list and the removed element in a Tuple. Elements are numbered from 0.
   * Example:
   * 
   * scala> removeAt(1, List('a, 'b, 'c, 'd))
   * res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)
   */
  def main(args: Array[String]) = {
    def test[T](name: String, f: (Int, List[Symbol]) => (List[T], T)) = {
      val l = List('a, 'b, 'c, 'd)

      println(name)
      println(f(0, l))
      println(f(1, l))
      println(f(2, l))
      println(f(3, l))
      //      println(f(-3, l))
      //      println(f(333, l))
      //      println(f(1, List()))
      //      println(f(-33, List()))
      println
    }

    test("removeAt", removeAt)
    test("removeAt2", removeAt2)
  }

  // recursive
  def removeAt[T](k: Int, l: List[T]): (List[T], T) = {
    (k, l) match {
      case (_, List()) => throw new NoSuchElementException
      case (0, h :: t) => (t, h)
      case (k, h :: t) => {
        val result = removeAt(k - 1, t)

        (h :: result._1, result._2)
      }
    }
  } 

  // split solution
  def removeAt2[T](k: Int, l: List[T]): (List[T], T) = {
    if (k < 0) throw new NoSuchElementException

    l.splitAt(k) match {
      case (s1, List()) => throw new NoSuchElementException
      case (s1, h2 :: t2) => (s1 ::: t2, h2)
    }
  }

}