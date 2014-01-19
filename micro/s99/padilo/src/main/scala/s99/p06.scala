package s99

/*
 * Find out whether a list is a palindrome.
 * Example:
 * scala> isPalindrome(List(1, 2, 3, 2, 1))
 * res0: Boolean = true
 */
object p06 {
  def main(args: Array[String]): Unit = {
    println(isPalindrome3(List(1, 2, 3, 2, 1)))
  }
  
  def isPalindrome[T](l:List[T]) = {
    l.reverse == l
  }

  def isPalindrome2[T](l:List[T]) = {
    def compareLists(l1:List[T], l2:List[T]):Boolean = {
      (l1,l2) match {
        case (h1::t1, h2::t2) if(h1 == h2) => compareLists(t1, t2)
        case (List(), List()) => true
        case _ => false
      }
    }
    
    compareLists(l, l.reverse)
  }
  
  def isPalindrome3[T](l:List[T]) = {
    def isPalindrome3Rec(l1:List[T], l2:List[T]):(List[T],Boolean) = {
      l2 match {
        case head::List() => (l1.tail, l1.head == head) 
        case head::tail => {
          val l1Remain = isPalindrome3Rec(l1, tail)
          
          (l1Remain._1.tail, l1Remain._2 && l1Remain._1.head == head)
        }
        case _ => (List(), true)
      }
    }
    
    isPalindrome3Rec(l, l)._2
  }

}