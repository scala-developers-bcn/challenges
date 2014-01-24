package s99

/*
 * Pack consecutive duplicates of list elements into sublists.
 * If a list contains repeated elements they should be placed in separate sublists.
 * Example:
 * 
 * scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
 * res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e))
 */
object P09 {
  def main(args: Array[String]): Unit = {
    println(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))
  }

  // Tail recursive first solution
  def pack[T](l: List[T]): List[List[T]] = {
    // Extracts from the list l the duplicates and returns (list of remains, list of duplicated elements). This can be done with span function if you know that exists see pack2. BTW this was funny :)
    def extractDuplicates[T](e: T, l: List[T], result: (List[T], List[T])): (List[T], List[T]) = {
      l match {
        case head :: tail if (head == e) => extractDuplicates(e, tail, (List(), head :: result._2))
        case _ => (l, result._2)
      }
    }
    
    // Tail recursive implementation
    def packR(l: List[T], result: List[List[T]]): List[List[T]] = {
      if (l.isEmpty) {
        result
      } else {
        val extractedList = extractDuplicates(l.head, l, (List(), List()))
        packR(extractedList._1, extractedList._2 :: result)
        
      }
    }

    packR(l, List())
  }

  // Tail recursive after I saw span function
  def pack2[T](l: List[T]): List[List[T]] = {
    // Tail recursive function
    def packR(l: List[T], result: List[List[T]]): List[List[T]] = {
      if (l.isEmpty) {
        result
      } else {
        val extractedList = l.span((_==l.head))
        packR(extractedList._2, extractedList._1 :: result)
        
      }
    }

    packR(l, List())
  }


  
  // This solution groups all duplicates of each element, this is not correct
  def packAll[T](l: List[T]): List[List[T]] = {
    l.groupBy((x => x)).values.toList
  }

}