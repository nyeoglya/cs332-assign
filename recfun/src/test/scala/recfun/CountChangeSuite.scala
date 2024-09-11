package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {
  import Main.countChange
  test("countChange: example given in instructions") {
    assert(countChange(4,List(1,2)) === 3)
  }

  test("countChange: sorted CHF") {
    assert(countChange(300,List(5,10,20,50,100,200,500)) === 1022)
  }

  test("countChange: no pennies") {
    assert(countChange(301,List(5,10,20,50,100,200,500)) === 0)
  }

  test("countChange: unsorted CHF") {
    assert(countChange(300,List(500,5,50,100,20,200,10)) === 1022)
  }

  // basic functionality test
  test("countChange: exact match") {
    assert(countChange(5, List(1, 2, 5)) === 4)
  }

  test("countChange: no change needed") {
    assert(countChange(0, List(1, 2, 5)) === 1)
  }

  test("countChange: no coins") {
    assert(countChange(5, List()) === 0)
  }

  test("countChange: single coin type") {
    assert(countChange(6, List(1)) === 1)
  }

  test("countChange: multiple coins") {
    assert(countChange(10, List(1, 2, 5)) === 10)
  }

  // boundary test
  test("countChange: negative money") {
    assert(countChange(-1, List(1, 2, 5)) === 0)
  }

  test("countChange: zero money with empty coins") {
    assert(countChange(0, List()) === 1)
  }

  test("countChange: zero money with non-empty coins") {
    assert(countChange(0, List(1, 2, 5)) === 1)
  }

  test("countChange: exact money with no coins") {
    assert(countChange(5, List()) === 0)
  }

  // exception test
  test("countChange: large coins, small money") {
    assert(countChange(1, List(5, 10, 25)) === 0)
  }

  test("countChange: duplicate coins") {
    assert(countChange(5, List(1, 1, 1, 2, 5)) === 4)
  }

  test("countChange: coin larger than money") {
    assert(countChange(5, List(10)) === 0)
  }

  test("countChange: empty money and empty coins") {
    assert(countChange(0, List()) === 1)
  }

  test("countChange: single large coin") {
    assert(countChange(5, List(10)) === 0)
  }
  
  // performance test
  test("countChange: large amount with few coins") {
    assert(countChange(1000, List(1, 5, 10, 25)) > 0)
  }
}
