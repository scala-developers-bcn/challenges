package org.scalabcn.s99

import org.scalatest.{Matchers, FlatSpec}
import org.scalatest.prop.PropertyChecks
import org.scalabcn.s99.Week1.{Penultimate, Last}

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

  def p02[T](xs: List[T], result: T) =
    for(func <- Penultimate.funcs[T])
      func(xs) should equal (result)

  "Penultimate (solution 2)" should "return the last but one element" in {
    forAll { (init: List[Int], penultimate: Int, last: Int) =>
      val complete = (last :: penultimate :: init).reverse
      p02(complete, penultimate)
    }
  }
}
