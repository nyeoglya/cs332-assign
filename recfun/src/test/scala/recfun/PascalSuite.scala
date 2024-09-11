package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {
  import Main.pascal

  // example
  test("pascal: col=0,row=2") {
    assert(pascal(0,2) === 1)
  }

  test("pascal: col=1,row=2") {
    assert(pascal(1,2) === 2)
  }

  test("pascal: col=1,row=3") {
    assert(pascal(1,3) === 3)
  }

  // basic functionality test
  test("pascal: col=1, row=1") {
    assert(pascal(1, 1) === 1)
  }

  test("pascal: col=2, row=2") {
    assert(pascal(2, 2) === 1)
  }

  test("pascal: col=2, row=3") {
    assert(pascal(2, 3) === 3)
  }

  // boundary test
  test("pascal: col=0, row=0") {
    assert(pascal(0, 0) === 1)
  }

  test("pascal: col=0, row=10") {
    assert(pascal(0, 10) === 1)
  }

  test("pascal: col=10, row=10") {
    assert(pascal(10, 10) === 1)
  }

  test("pascal: col=5, row=5") {
    assert(pascal(5, 5) === 1)
  }

  // exception test
  test("pascal: negative column") {
    val result = try {
      pascal(-1, 5)
      false // Should not reach here
    } catch {
      case _: IllegalArgumentException => true
      case _: Throwable => false // Unexpected exception type
    }
    assert(result, "Expected IllegalArgumentException for negative column")
  }

  test("pascal: negative row") {
    val result = try {
      pascal(5, -1)
      false // Should not reach here
    } catch {
      case _: IllegalArgumentException => true
      case _: Throwable => false // Unexpected exception type
    }
    assert(result, "Expected IllegalArgumentException for negative row")
  }

  test("pascal: column greater than row") {
    val result = try {
      val value = pascal(6, 5)
      value == 0 // Expecting 0 for column > row
    } catch {
      case _: Throwable => false // Unexpected exception
    }
    assert(result, "Expected result to be 0 when column > row")
  }

  test("pascal: column and row are negative") {
    val result = try {
      pascal(-1, -1)
      false // Should not reach here
    } catch {
      case _: IllegalArgumentException => true
      case _: Throwable => false // Unexpected exception type
    }
    assert(result, "Expected IllegalArgumentException for negative column and row")
  }

  test("pascal: very large column") {
    val result = try {
      val value = pascal(1000, 10)
      true // If no exception, the test passes
    } catch {
      case _: StackOverflowError => false // Expected for very large column
      case _: Throwable => false // Unexpected exception
    }
    assert(result, "Expected StackOverflowError for very large column")
  }

  // performance test
  test("pascal: large column and row") {
    val startTime = System.currentTimeMillis()
    assert(pascal(20, 20) > 0)
    val endTime = System.currentTimeMillis()
    println(s"Time taken for pascal(20, 20): ${endTime - startTime} ms")
  }

  test("pascal: large row, small column") {
    val startTime = System.currentTimeMillis()
    assert(pascal(5, 15) > 0)
    val endTime = System.currentTimeMillis()
    println(s"Time taken for pascal(5, 15): ${endTime - startTime} ms")
  }

  test("pascal: medium row and column") {
    val startTime = System.currentTimeMillis()
    assert(pascal(10, 10) > 0)
    val endTime = System.currentTimeMillis()
    println(s"Time taken for pascal(10, 10): ${endTime - startTime} ms")
  }

  test("pascal: very large row and column") {
    val startTime = System.currentTimeMillis()
    assert(pascal(30, 30) > 0)
    val endTime = System.currentTimeMillis()
    println(s"Time taken for pascal(30, 30): ${endTime - startTime} ms")
  }
}
