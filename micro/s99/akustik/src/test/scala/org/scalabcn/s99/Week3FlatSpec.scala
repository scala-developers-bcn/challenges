package org.scalabcn.s99

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalabcn.s99.Week3._

@RunWith(classOf[JUnitRunner])
class Week3FlatSpec extends FlatSpec with ShouldMatchers {

  "P28" should "sort a list of lists by its length" in {
    lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))) should be(List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l)))

  }

  it should "sort a list of lists by iths length frequency" in {
    lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o))) should be(List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n)))
  }
 
  "P27" should "generate combinations of groups of elements of a list" in {
    var comb = group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
    comb.map (c => {
      c.length should be(3)
      assert(c.filter(_.length == 2).length === 2)
      assert(c.filter(_.length == 5).length === 1)
      assert(c.flatten.length === 9)
      assert(c.flatten.groupBy(e => e).toList.length === 9)
    })
  }

  "P26" should "generate the exact number of ways to choose k elements from a set of n" in {
    val c = combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
    c.length should be(20)
    assert(c.filter(e => e.length != 3).length === 0)
    assert(c.contains(List('a, 'b, 'c)))
    assert(c.contains(List('a, 'b, 'd)))
    assert(!c.contains(List('b, 'a, 'c)))
  }

  it should "generate an empty list when k is 0" in {
    combinations(0, List('a, 'b)) should be(List())
  }
  
  "P25" should "generates a random permutation of the list" in {
    val x = List('a, 'b, 'c, 'd, 'e, 'f)
    val elements = randomPermute(x)
    assert(elements.length === x.length)
    assert(elements.filterNot(e => x.contains(e)).length === 0)
  }

  "P24" should "extract a given number of random selected elements from the set 1 to M" in {
    val elements = lotto(6, 49)
    assert(elements.length === 6)
    assert(elements.filterNot(e => (1 to 49).contains(e)).length === 0)
  }

  it should "throw a IllegalArgumentException when the amount is bigger than the set size" in {
    intercept[java.lang.IllegalArgumentException]{
      lotto(42, 6)
    } 
  }

  it should "throw a IllegalArgumentException when the amount is negative" in {
    intercept[java.lang.IllegalArgumentException]{
      lotto(-1, 49)
    }
  }

  "P23" should "extract a given number of randomly selected elements from a list" in {
    val x = List('a, 'b, 'c, 'd, 'f, 'g, 'h)
    val elements = randomSelect(3, x)
    assert(elements.length === 3)
    assert(elements.filterNot(e => x.contains(e)).length === 0) 
  }

  it should "throw a IllegalArgumentException when the amount is bigger than the list length" in {
    intercept[java.lang.IllegalArgumentException]{
      randomSelect(42, List('a))
    }
  }
      

  it should "throw a IllegalArgumentException when the amount is negative" in {
    intercept[java.lang.IllegalArgumentException]{
      randomSelect(-1, List('a, 'b))
    }
  }

  "P22" should "return a list with the elements in the selection range" in {
    range(4, 9) should be(List(4, 5, 6, 7, 8, 9))
  }

  it should "return a list when the selection range is negative" in {
    range(-1, 2) should be(List(-1, 0, 1, 2))
  }

  it should "throw IllegalArgumentException when a > b" in {
    intercept[java.lang.IllegalArgumentException]{
      range(-4, -9) 
    }
  }

  it should "return a list of one element if the selection range has the same values" in {
    range(1, 1) should be(List(1))
  }

  "P21" should "insert an element in a given position of a list" in {
    insertAt('new, 1, List('a, 'b, 'c, 'd)) should be(List('a, 'new, 'b, 'c, 'd))
  }

  it should "insert an element in the last position of the list" in {
    insertAt('new, 1, List('a)) should be(List('a, 'new))
  }

  it should "throw an ArrayIndexOutOfBoundsException if the index is negative" in {
    intercept[java.lang.ArrayIndexOutOfBoundsException]{
      insertAt('new, -1, List('a, 'b , 'c, 'd))
    }    
  }

  it should "throw an ArrayIndexOutOfBoundsException if the index is greater than the list length" in {
    intercept[java.lang.ArrayIndexOutOfBoundsException]{
      insertAt('new, 42, List('a, 'b, 'c, 'd))
    } 
  }


/*

  it should "raise a IllegalArgumentException when the index is out of bounds" in {
    intercept[IllegalArgumentException]{
      removeAt(5, List('a, 'b))
    }
    intercept[IllegalArgumentException]{
      removeAt(-1, List('a))
    }
  }
*/
}
