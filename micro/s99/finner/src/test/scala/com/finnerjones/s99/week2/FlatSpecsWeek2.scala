package com.finnerjones.s99.week2

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import com.finnerjones.s99.week2.S99SolutionsWeek2._

/*
 * Taken from S-99 problems
 * http://aperiodic.net/phil/scala/s-99/
 */
class FlatSpecsWeek2 extends FlatSpec with Matchers {

  // P11 - using pack(l) from P10
  "packModified(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      packModified(l) should be(List((4, 'a'), 'b', (2, 'c'), (2, 'a'), 'd', (4, 'e')))
    }

  // P11 - using encode(l) from P10
  "encodeModified(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      encodeModified(l) should be(List((4, 'a'), 'b', (2, 'c'), (2, 'a'), 'd', (4, 'e')))
    }

  // P11 - using encode(l) from P10
  "encodeModified2(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
    "return List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))" in {
      val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
      encodeModified2(l) should be(List(Right(4, 'a'), Left('b'), Right(2, 'c'), Right(2, 'a'), Left('d'), Right(4, 'e')))
    }

  // P12 
  "decode(List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')))" should
    "List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')" in {
      val l = List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e'))
      decode(l) should be(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
    }

  // P12 
  "decode1(List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e')))" should
    "List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')" in {
      val l = List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e'))
      decode1(l) should be(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))
    }

  
  // P13 first draft implementing both pack and encode in one def
  "encodeDirect(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
  "return List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))" in {
    val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    encodeDirect(l) should be (List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e')))
  }

  // P13 second draft solution based on the web pack() P09
  "encodeD2(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
  "return List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))" in {
    val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    encodeD2(l) should be (List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e')))
  }

  
  // P13 third draft solution base on my pack() solution P09
  "encodeD3(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e'))" should
  "return List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))" in {
    val l = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    encodeD3(l) should be (List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e')))
  }
  
  // P14 - my solution 
  "duplicate(List('a', 'b', 'c', 'c', 'd'))" should 
  "return List('a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'd', 'd')" in {
    val l = List('a', 'b', 'c', 'c', 'd')
    duplicate(l) should be (List('a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'd', 'd'))
  }
  
  // P15 
  "duplicateN(3, List('a', 'b', 'c', 'c', 'd'))" should
  "return List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd')" in {
    val l = List('a', 'b', 'c', 'c', 'd')
    duplicateN(3,l) should be (List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd'))
  }
  
  // P15 from website
  "duplicate2N(3, List('a', 'b', 'c', 'c', 'd'))" should
  "return List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd')" in {
    val l = List('a', 'b', 'c', 'c', 'd')
    duplicate2N(3,l) should be (List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'd', 'd', 'd'))
  }
 
  
  // P16 - my solution
  "drop(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))" should
  "return List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')" in {
    val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
    drop(3,l) should be (List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k'))
    
  }
  
  // P16 - web solution recursive
  "dropRecursive(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))" should
  "return List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')" in {
    val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
    dropRecursive(3,l) should be (List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k'))
    
  }

  
   // P16 - web solution tail recursive
  "dropTailRecursive(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))" should
  "return List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')" in {
    val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
    dropTailRecursive(3,l) should be (List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k'))
  }
 
   // P16 - web solution functional
  "dropFunctional(3, List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'))" should
  "return List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k')" in {
    val l = List('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k')
    dropFunctional(3,l) should be (List('a', 'b', 'd', 'e', 'g', 'h', 'j', 'k'))
  }

  
}