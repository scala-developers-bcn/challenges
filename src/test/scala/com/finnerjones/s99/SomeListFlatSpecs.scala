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
	
}