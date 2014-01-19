package org.scalabcn.challenges.micro.s99

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ListsSuite extends FunSuite {
  import org.scalabcn.challenges.micro.s99.Lists._
  
  // ***************************************************
  // Tests for P01 (*) Find the last element of a list.
  // ***************************************************
  test("Happy path: last should return last element on non empty list") {
    // act
    val result = last(List(1,2,3,4))
    
    // assert
    assert(result === 4, "Last element in list should be 4")    
  }
  
  test("Empty list: last should throw IllegalArgumentException on empty list") {
    // act
    intercept[IllegalArgumentException] {
    	val result = last(List())
    }          
  }
  
  // ***************************************************
  // Tests for P02 (*) Find the last but one element of a list.
  // ***************************************************
  test("Happy path: penultimate should return penultimate element on a list with 2 or more elements") {
    // act
    val result = penultimate(List(1,2))
    
    // assert
    assert(result === 1, "Penultimate element in list should be 1")    
  }
  
  test("Empty list: penultimate should throw IllegalArgumentException on empty list") {
     // act
    intercept[IllegalArgumentException] {
    	val result = penultimate(List())
    }      
  }
  
  test("One element list: penultimate should throw IllegalArgumentException on list with one element") {
     // act
    intercept[IllegalArgumentException] {
    	val result = penultimate(List(1))
    }      
  }
  
  // ***************************************************
  // Tests for P03 (*) Find the Kth element of a list.
  // By convention, the first element in the list is element 0. 
  // ***************************************************
  test("Happy path: nth should return kth element on a lis with k or more elements") {
    // act
    val result = nth(0,List(1))
    
    // assert
    assert(result === 1, "0 element in list should be 1")    
  }
  
  test("Empty list: nth should throw NoSuchElementException on empty list") {
     // act
    intercept[NoSuchElementException] {
    	val result = nth(0,List())
    }      
  }
  
  test("List with fewer items: nth should throw NoSuchElementException on list with fewer items") {
     // act
    intercept[NoSuchElementException] {
    	val result = nth(1,List(1))
    }      
  }
  
  // ***************************************************
  // P04 (*) Find the number of elements of a list.
  // Recursive implementation
  // ***************************************************
  test("Happy path: length should return the number of elements of a list.") {
    // act
    val result = length(List(1))
    
    // assert
    assert(result === 1, "length should be 1")    
  }
  
  test("Empty list: length should return 0 on a empty list.") {
    // act
    val result = length(List())
    
    // assert
    assert(result === 0, "length should be 0 on a empty list.")    
  }
  
  // ***************************************************
  // P04 (*) Find the number of elements of a list. Implementation with 
  // foldLefth
  // ***************************************************
  test("Happy path: length with fold should return the number of elements of a list.") {
    // act
    val result = lengthWithFold(List(1))
    
    // assert
    assert(result === 1, "length with fold should be 1")    
  }
  
  test("Empty list: length with fold should return 0 on a empty list.") {
    // act
    val result = lengthWithFold(List())
    
    // assert
    assert(result === 0, "length with fold should be 0 on a empty list.")    
  }
  
  // ***************************************************
  // P05 (*) Reverse a list.
  // ***************************************************
  test("Happy path: reverse should reverse the list.") {
    // act
    val result = reverse(List(1,2,3,4,5))
    
    // assert
    assert(result === List(5,4,3,2,1), "reverse should reverse the list")    
  }
  
  test("Empty list: reverse with empty list returns Nil.") {
    // act
    val result = reverse(List())
    
    // assert
    assert(result === Nil, "reverse with empty list returns Nil.")    
  }
  
  // ***************************************************
  // P05 (*) Reverse a list. Implementation not linear
  // ***************************************************
  test("Happy path: reverse not linear should reverse the list.") {
    // act
    val result = reverseNotLinear(List(1,2,3,4,5))
    
    // assert
    assert(result === List(5,4,3,2,1), "reverse not linear should reverse the list")    
  }
  
  test("Empty list: reverse not linear with empty list returns Nil.") {
    // act
    val result = reverseNotLinear(List())
    
    // assert
    assert(result === Nil, "reverse not linear with empty list returns Nil.")    
  }
  
  // ***************************************************
  // P05 (*) Reverse a list. Implementation with 
  // foldLefth
  // ***************************************************
  test("Happy path: reverse with fold should reverse the list.") {
    // act
    val result = reverse(List(1,2,3,4,5))
    
    // assert
    assert(result === List(5,4,3,2,1), "reverse with fold should reverse the list")    
  }
  
  test("Empty list: reverse with fold with empty list returns Nil.") {
    // act
    val result = reverseWithFold(List())
    
    // assert
    assert(result === Nil, "reverse with fold with empty list returns Nil.")    
  }
  
  // ***************************************************
  // P06 (*) Find out whether a list is a palindrome.
  // ***************************************************
  test("Happy path: isPalindrome with palindorme list should return true") {
    // act
    val result = isPalindrome(List(1,2,3,2,1))
    
    // assert
    assert(result, "isPalindrome with palindorme list should return true")    
  }
  
  test("Not palindrome: isPalindrome with not palindorme list should return false") {
    // act
    val result = isPalindrome(List(1,2))
    
    // assert
    assert(!result, "isPalindrome with not palindorme list should return false")    
  }
  
  test("Empty list: isPalindrome with empty list should return true") {
    // act
    val result = isPalindrome(List())
    
    // assert
    assert(result, "isPalindrome with empty list should return true")    
  }
  
  // ***************************************************
  // P07 (**) Flatten a nested list structure.
  // ***************************************************
  test("Happy path: flatten should flatten all lists") {
    // act
    val result = flatten(List(List(1, 1), 2, List(3, List(5, 8))))
    
    // assert
    assert(result === List(1, 1, 2, 3, 5, 8), "flatten should flatten all lists")    
  }
  
  test("Another happy path: flatten should flatten all lists") {
    // act
    val result = flatten(List(List(3, 1), 2, List(7),List(3, List(5, 8))))
    
    // assert
    assert(result === List(3, 1, 2, 7, 3, 5, 8), "flatten should flatten all lists")    
  }
  
  test("Empty list: flatten should return Nil on nested empty lists") {
    // act
    val result = flatten(List(List(), List(),List(List())))
    
    // assert
    assert(result === Nil, "flatten should return Nil on nested empty lists")    
  }
  // ***************************************************
  // P08 (**) Eliminate consecutive duplicates of list elements.
  // ***************************************************
  test("Happy path: compress should eliminate consecutive duplicates") {
    // act
    val result = compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List('a, 'b, 'c, 'a, 'd, 'e), "compress should eliminate consecutive duplicates")    
  }
  
  test("Empty list: compress should return Nil on empty list") {
    // act
    val result = compress(List())
    
    // assert
    assert(result === Nil, "compress should return Nil on empty list")    
  }
  
  // ***************************************************
  // P08 (**) Eliminate consecutive duplicates of list elements with fold.
  // ***************************************************
  test("Happy path: compress should eliminate consecutive duplicates with fold.") {
    // act
    val result = compressWithFold(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List('a, 'b, 'c, 'a, 'd, 'e), "compress should eliminate consecutive duplicates with fold.")    
  }
  
  test("Empty list: compress with fold should return Nil on empty list") {
    // act
    val result = compressWithFold(List())
    
    // assert
    assert(result === Nil, "compress with fold should return Nil on empty list")    
  }
  
  // ***************************************************
  // P09 (**) Pack consecutive duplicates of list elements into sublists
  // ***************************************************
  test("Happy path: pack should Pack consecutive duplicates of list elements into sublists.") {
    // act
    val result = pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List(List('a, 'a, 'a, 'a), List('b), List('c, 'c), List('a, 'a), List('d), List('e, 'e, 'e, 'e)), "pack should Pack consecutive duplicates of list elements into sublists.")    
  }
  
  test("Empty list: pack should return Nil on empty list") {
    // act
    val result = pack(List())
    
    // assert
    assert(result === Nil, "pack should return Nil on empty list")    
  }
  
  // ***************************************************
  // P10 (*) Run-length encoding of a list.
  //  Use the result of problem P09 to implement the so-called run-length encoding data compression method. 
  // Consecutive duplicates of elements are encoded as tuples (N, E) where N is the number of duplicates of the element E. 
  // ***************************************************
  test("Happy path: encode should encode.") {
    // act
    val result = encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)), "Happy path: encode should encode.")    
  }
  
  test("Empty list: encode should return Nil on empty list") {
    // act
    val result = encode(List())
    
    // assert
    assert(result === Nil, "encode should return Nil on empty list")    
  }
  
  // ***************************************************
  // P10 (*) Run-length encoding of a list.
  //  Modify the result of problem P10 in such a way that if an element has no duplicates it is simply copied into the result list. 
  // Only elements with duplicates are transferred as (N, E) terms. 
  // ***************************************************
  test("Happy path: encodeWithoutOneElementTupples should encode.") {
    // act
    val result = encodeWithoutOneElementTupples(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List((4,'a), 'b, (2,'c), (2,'a),'d, (4,'e)), "Happy path: encodeWithoutOneElementTupples should encode.")    
  }
  
  test("Empty list: encodeWithoutOneElementTupples should return Nil on empty list") {
    // act
    val result = encodeWithoutOneElementTupples(List())
    
    // assert
    assert(result === Nil, "encodeWithoutOneElementTupples should return Nil on empty list")    
  }
  
  // ***************************************************
  // P12 (**) Decode a run-length encoded list
  //  Given a run-length code list generated as specified in problem P10, construct its uncompressed version.  
  // ***************************************************
  test("Happy path: decode should decode.") {
    // act
    val result = decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
    
    // assert
    assert(result === List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e), "Happy path: decode should decode.")    
  }
  
  test("Empty list: decode should return Nil on empty list") {
    // act
    val result = decode(List())
    
    // assert
    assert(result === Nil, "decode should return Nil on empty list")    
  }
  
  // ***************************************************
  // P12 (**) Decode a run-length encoded list
  //  Given a run-length code list generated as specified in problem P10, construct its uncompressed version.  
  // ***************************************************
  test("Happy path: decode with map should decode.") {
    // act
    val result = decodeWithMap(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
    
    // assert
    assert(result === List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e), "Happy path: decode with map should decode.")    
  }
  
  test("Empty list: decode with map should return Nil on empty list") {
    // act
    val result = decodeWithMap(List())
    
    // assert
    assert(result === Nil, "decode with map should return Nil on empty list")    
  }
  
  // ***************************************************
  // P12 (**) Decode a run-length encoded list
  //  Given a run-length code list generated as specified in problem P10, construct its uncompressed version.  
  // ***************************************************
  test("Happy path: decode with fold should decode.") {
    // act
    val result = decodeWithFold(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
    
    // assert
    assert(result === List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e), "Happy path: decode with fold should decode.")    
  }
  
  test("Empty list: decode with fold should return Nil on empty list") {
    // act
    val result = decodeWithFold(List())
    
    // assert
    assert(result === Nil, "decode with fold should return Nil on empty list")    
  }
  
  // ***************************************************
  // P13 (**) Run-length encoding of a list (direct solution).
  // Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly. 
  // ***************************************************
  test("Happy path: encode direct should encode.") {
    // act
    val result = encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)), " encode direct should encode.")    
  }
  
  test("Empty list: encode direct should return Nil on empty list") {
    // act
    val result = encodeDirect(List())
    
    // assert
    assert(result === Nil, "encode direct should return Nil on empty list")    
  }
  
  // ***************************************************
  // P13 (**) Run-length encoding of a list (direct solution).
  // Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly. 
  // ***************************************************
  test("Happy path: encode direct with fold should encode.") {
    // act
    val result = encodeDirectWithFold(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    
    // assert
    assert(result === List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e)), " encode direct with fold should encode.")    
  }
  
  test("Empty list: encode direct with fold should return Nil on empty list") {
    // act
    val result = encodeDirectWithFold(List())
    
    // assert
    assert(result === Nil, "encode direct with fold should return Nil on empty list")    
  }
  
  // ***************************************************
  // P14 (*) Duplicate the elements of a list.  
  // ***************************************************
  test("Happy path: duplicate should duplicate the elements of a list.") {
    // act
    val result = duplicate(List('a, 'b, 'c, 'c, 'd))
    
    // assert
    assert(result === List('a, 'a, 'b, 'b, 'c, 'c, 'c, 'c, 'd, 'd), "duplicate should duplicate the elements of a list.")    
  }
  
  test("Empty list: duplicate should return Nil on empty list") {
    // act
    val result = duplicate(List())
    
    // assert
    assert(result === Nil, "duplicate should return Nil on empty list")    
  }
  
  // ***************************************************
  // P15 (**) Duplicate the elements of a list a given number of times. 
  // ***************************************************
  test("Happy path: duplicateN should duplicate the elements of a list a given number of times.") {
    // act
    val result = duplicateN(3, List('a, 'b, 'c, 'c, 'd))
    
    // assert
    assert(result === List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd), "duplicateN should duplicate the elements of a list a given number of times.")    
  }
  
  test("Empty list: duplicateN should return Nil on empty list") {
    // act
    val result = duplicateN(3, List())
    
    // assert
    assert(result === Nil, "duplicateN should return Nil on empty list")    
  }
  
  // ***************************************************
  // P16 (**) Drop every Nth element from a list.
  // In sample list starts with 1 and not with 0 as in nth
  // ***************************************************
  test("Happy path: drop should drop Nth element from a list.") {
    // act
    val result = drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    
    // assert
    assert(result === List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k), "drop should drop Nth element from a list.")    
  }
  
  test("Empty list: drop should throw NoSuchElementException on empty list") {
     // act
    intercept[NoSuchElementException] {
    	val result = drop(0,List())
    }      
  }
  
  test("List with fewer items: drop should throw NoSuchElementException on list with fewer items") {
     // act
    intercept[NoSuchElementException] {
    	val result = drop(2,List(1))
    }      
  }
  
  // ***************************************************
  // P17 (*) Split a list into two parts.
  // The length of the first part is given. Use a Tuple for your result. 
  // Note: Implementation with splitAt is trivial, I'm not use this function
  // ***************************************************
  test("Happy path: split should split the list.") {
    // act
    val result = split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    
    // assert
    assert(result === (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k)), "split should split the list.")    
  }
  
  test("Empty list: split should return Nil on empty list") {
     // act
    val result = split(3, List())
    
    // assert
    assert(result === (Nil, Nil), "split should return (Nil, Nil) on empty list")    
  }
  
  test("List with fewer items: split should return (original, Nil) on list with fewer items") {
    val result = split(3, List('a, 'b))
    
    // assert
    assert(result === (List('a, 'b), Nil), "split should return (original, Nil) on list with fewer items") 
  }
  
  // ***************************************************
  // P18 (**) Extract a slice from a list.
  // Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. 
  // Start counting the elements with 0.  
  // Note: Implementation with slice is trivial, I'm not use this function
  // ***************************************************
  test("Happy path: slice should return the correct slice.") {
    // act
    val result = slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    
    // assert
    assert(result === List('d, 'e, 'f, 'g), "slice should return the correct slice.")    
  }
  
  test("Empty list: slice should return Nil on empty list") {
     // act
    val result = slice(1, 3, List())
    
    // assert
    assert(result === Nil, "slice should return Nil on empty list")    
  }
  
  test("List with fewer items: slice should return the original list items from Ith to the end on list with fewer items") {
    val result = slice(1, 3, List('a, 'b))
    
    // assert
    assert(result === List('b), "slice should return the original list items from Ith to the end on list with fewer items") 
  }
  
  // ***************************************************
  // P19 (**) Rotate a list N places to the left.  
  // ***************************************************
  test("Happy path: rotate with positive numbers.") {
    // act
    val result =rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    
    // assert
    assert(result === List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c), "rotate with positive numbers.")    
  }
  
  test("Happy path: rotate with negative numbers.") {
    // act
    val result =rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    
    // assert
    assert(result === List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i), "rotate with negative numbers.")    
  }
  
  test("Empty list: rotate should return Nil on empty list") {
     // act
    val result = rotate(1, List())
    
    // assert
    assert(result === Nil, "rotate should return Nil on empty list")    
  }
  
  // ***************************************************
  // P20 (*) Remove the Kth element from a list.
  // ***************************************************
  test("Happy path: Remove the Kth element from a list.") {
    // act
    val result = removeAt(1, List('a, 'b, 'c, 'd))
    
    // assert
    assert(result === (List('a, 'c, 'd),'b), "Remove the Kth element from a list.")    
  }
    
  test("Empty list: removeAt should throw NoSuchElementException on empty list") {
     // act
    intercept[NoSuchElementException] {
    	val result = removeAt(1, List())
    }      
  }
  
  test("List with fewer items: removeAt should throw NoSuchElementException on list with fewer items") {
     // act
    intercept[NoSuchElementException] {
    	val result = removeAt(1,List(1))
    }      
  }      
}
