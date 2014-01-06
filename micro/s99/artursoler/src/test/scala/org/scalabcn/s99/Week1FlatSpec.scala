package org.scalabcn.s99

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.PropertyChecks
import org.scalabcn.s99.Week1.Last

class Week1FlatSpec extends FlatSpec with Matchers with PropertyChecks {
  def p01[T](xs: List[T], result: T) =
    for(func <- Last.funcs[T])
      func(xs) should equal (result)

  "Last (solution 1)" should "return last element of list" in {
    forAll { (init: List[Int], last: Int) =>
      val complete = (last :: init).reverse
      p01(complete, last)
    }
  }
}
