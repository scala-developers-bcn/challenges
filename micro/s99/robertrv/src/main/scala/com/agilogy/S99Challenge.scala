package com.agilogy

class S99Challenge {

  def last(x: List[Int]) = if (x.isEmpty) Nil else x.foldLeft(x.head)((_,c) => c)

  def penultimate(x: List[Int]) = if (x.size < 2) Nil else x.foldLeft((x.head, x.tail.head))((r,c) => (r._2,c))._1

  def nth(position: Int, x: List[Int]):Int =
    if (x.isEmpty)
      throw new IndexOutOfBoundsException
    else if (position == 0)
      x.head
    else {
      nth(position-1,x.tail)
    }

  def length[T](xs:List[T]):Int = xs match {
    case Nil => 0
    case _ => 1 + length(xs.tail)
  }

  def reverse[T](xs: List[T]):List[T] = reverse_folding(xs)

  private def reverse_folding[T](xs: List[T]):List[T] =
    (List[T]() /: xs)((acc, x) => x :: acc) //=== xs.foldLeft(List[T]())((acc, x) => x :: acc)

  private def reverse_recursive[T](xs: List[T]):List[T] =
  {
    def helper(acc:List[T], actual:List[T]): List[T] = actual match {
      case Nil => acc
      case x :: ys => helper(x :: acc, ys)
    }
    helper(Nil, xs)
  }

  def isPalindrome[T](xs: List[T]): Boolean =
    xs.take(xs.length/2).reverse == xs.takeRight(xs.length/2)

  def flatten(xs: List[_]):List[_] = xs flatMap {
    case a: List[_] => flatten(a)
    case x => List(x)
  }

  def compress[T](xs: List[T]):List[T] = xs.foldRight(List[T]()) {
    (x, list) => {
      if (list.isEmpty || list.head != x) x :: list
      else list
    }
  }

  def pack[T](xs: List[T]): List[List[T]] = xs.foldRight(List[List[T]]()) {
    (x, list) => {
      if (list.isEmpty || list.head.head != x) List(x) :: list
      else (x :: list.head) :: list.tail
    }
  }

  def encode[T](xs:List[T]): List[(Int, T)] = pack(xs).map(list => (list.size,list.head))

  def encodeModified[T](xs:List[T]): List[Any] = pack(xs).map(list => {
    if (list.size > 1) (list.size,list.head)
    else list.head
  })

  def decode[T](xs: List[(Int, T)]):List[T] = xs.flatMap(e => (1 to e._1).map(_ => e._2))
}
