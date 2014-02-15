package com.finnerjones.s99

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import com.finnerjones.s99.ListUitls._

class SomeListFlatSpecs extends FlatSpec with Matchers  {

	// P01 on S99
  
	"last(List(1,2,3,4,5,6,7,8))" should "result in 8" in {
	  val l = List(1,2,3,4,5,6,7,8)
	  last(l) should be (8)
	}
	
	"last(List(one,two,three,four,five)" should "results in (five)" in {
	  val l = List("one", "two","three", "four", "five")
	  last(l) should be ("five")
	} 

	// P02 on S99
	"penultimate(List(1,2,3,4,5,6,7,8))" should "result in (7)" in {
	  val l = List(1,2,3,4,5,6,7,8)
	  penultimate(l) should be (7)
	}
	
	"penultimate(List(four,three,seven,four,nine))" should "result in (four)" in {
	  val l = List("four","three","seven","four","nine")
	  penultimate(l) should be ("four")
	}
	
	
	// P03 on S99
	"nth(List(3, List(1,2,3,4,5,6,7,8)))" should "return (4)" in {
	  val l = List(1,2,3,4,5,6,7,8)
	  nth(3, l) should be (4)
	}
	
	"nth(List(4, List(9,6,7,3,6,1,2)))" should "return (6)" in {
	  val l = List(9,6,7,3,6,1,2)
	  nth(4, l) should be (6)
	}
	
	"nth(List(2, List(the brown fox jumped)))" should "return (fox)" in {
	  val l = List("the","brown","fox","jumped")
	  nth(2, l) should be ("fox")
	}
	
	
	
}