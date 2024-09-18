package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  
  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  /*
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }
  */

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  /*
  test("adding ints") {
    assert(1 + 2 === 3)
  }
  */

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  // functional test
  test("intersect contains only common elements") {
  new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect does not contain 1")
      assert(!contains(s, 2), "Intersect does not contain 2")
    }
  }

  test("diff contains elements in first set but not in second") {
    new TestSets {
      val s = diff(s1, s2)
      assert(contains(s, 1), "Diff contains 1")
      assert(!contains(s, 2), "Diff does not contain 2")
    }
  }

  test("filter returns subset that satisfies the predicate") {
    new TestSets {
      val s = filter(s1, x => x == 1)
      assert(contains(s, 1), "Filter contains 1")
      assert(!contains(s, 2), "Filter does not contain 2")
    }
  }

  // boundary test
  test("singletonSet contains only one element") {
    new TestSets {
      assert(!contains(s1, 2), "Singleton does not contain 2")
    }
  }

  test("union of a set with itself contains unique elements") {
    new TestSets {
      val s = union(s1, s1)
      assert(contains(s, 1), "Union contains 1")
      assert(!contains(s, 2), "Union does not contain 2")
    }
  }

  test("intersect of two disjoint sets is empty") {
    new TestSets {
      val s = intersect(s1, s2)
      assert(!contains(s, 1), "Intersect does not contain 1")
      assert(!contains(s, 2), "Intersect does not contain 2")
    }
  }

  test("diff with an empty set returns the original set") {
    new TestSets {
      val s = diff(s1, singletonSet(0))
      assert(contains(s, 1), "Diff contains 1")
    }
  }

  test("filter on an empty set returns an empty set") {
    val emptySet: Set = _ => false
    val s = filter(emptySet, x => x == 1)
    assert(!contains(s, 1), "Filter does not contain 1")
  }

  // exception handling test
  test("contains on empty set returns false") {
    val emptySet: Set = _ => false
    assert(!contains(emptySet, 1), "Empty set does not contain 1")
  }

  test("union of large sets should not throw an error") {
    val largeSet1: Set = x => x < 1000000
    val largeSet2: Set = x => x >= 1000000
    val s = union(largeSet1, largeSet2)
    assert(contains(s, 500000), "Union contains 500000")
    assert(contains(s, 1000000), "Union contains 1000000")
  }

  test("intersect on large sets should not throw an error") {
    val largeSet1: Set = x => x % 2 == 0
    val largeSet2: Set = x => x % 3 == 0
    val s = intersect(largeSet1, largeSet2)
    assert(contains(s, 6), "Intersect contains 6")
  }

  test("diff with empty set does not throw exception") {
    new TestSets {
      val emptySet: Set = _ => false
      val s = diff(s1, emptySet)
      assert(contains(s, 1), "Diff contains 1")
    }
  }

  test("filter with invalid predicate returns empty set") {
    new TestSets {
      val s = filter(s1, _ => try {
        throw new Exception("Invalid predicate")
      } catch {
        case _: Exception => false
      })
      assert(!contains(s, 1), "Filter does not contain 1 due to exception")
    }
  }

  // performance test
  test("union of large sets performance test") {
    val largeSet1: Set = x => x <= 1000000
    val largeSet2: Set = x => x > 1000000 && x <= 2000000
    val startTime = System.nanoTime()
    val s = union(largeSet1, largeSet2)
    val endTime = System.nanoTime()
    assert(endTime - startTime < 1000000000, "Union operation should complete within 1 second")
  }

  test("intersect of large sets performance test") {
    val largeSet1: Set = x => x % 2 == 0
    val largeSet2: Set = x => x % 3 == 0
    val startTime = System.nanoTime()
    val s = intersect(largeSet1, largeSet2)
    val endTime = System.nanoTime()
    assert(endTime - startTime < 1000000000, "Intersect operation should complete within 1 second")
  }

  test("diff of large sets performance test") {
    val largeSet1: Set = x => x < 1000000
    val largeSet2: Set = x => x >= 500000 && x < 1500000
    val startTime = System.nanoTime()
    val s = diff(largeSet1, largeSet2)
    val endTime = System.nanoTime()
    assert(endTime - startTime < 1000000000, "Diff operation should complete within 1 second")
  }

  test("filter on large sets performance test") {
    val largeSet: Set = x => x <= 1000000
    val startTime = System.nanoTime()
    val s = filter(largeSet, x => x % 1000 == 0)
    val endTime = System.nanoTime()
    assert(endTime - startTime < 1000000000, "Filter operation should complete within 1 second")
  }

  test("contains on large sets performance test") {
    val largeSet: Set = x => x <= 1000000
    val startTime = System.nanoTime()
    assert(contains(largeSet, 999999), "Contains operation should return true")
    val endTime = System.nanoTime()
    assert(endTime - startTime < 100000000, "Contains operation should complete within 0.1 second")
  }

  // forall - functional test
  test("forall returns true for all elements satisfying predicate") {
    new TestSets {
      val s = union(s1, s2)
      assert(forall(s, x => x > 0), "All elements are positive")
    }
  }

  test("forall returns false when one element does not satisfy predicate") {
    new TestSets {
      val s = union(s1, s2)
      assert(!forall(s, x => x == 1), "Not all elements are 1")
    }
  }

  // forall - boundary test
  test("forall handles empty set correctly") {
    assert(forall(x => false, x => true), "Empty set satisfies any predicate")
  }

  test("forall handles large range of elements") {
    val largeSet = (x: Int) => x >= -bound && x <= bound
    assert(forall(largeSet, x => x <= bound), "All elements are within bounds")
  }

  // forall - exception handling test
  test("forall handles invalid predicate gracefully") {
    new TestSets {
      val s = s1
      intercept[Exception] {
        forall(s, x => throw new IllegalArgumentException("Invalid Predicate"))
      }
    }
  }

  // forall - performance test
  test("forall checks performance with complex predicate") {
    new TestSets {
      val s = union(s1, s2)
      val complexPredicate = (x: Int) => (1 to 1000).forall(n => x % n != 0)
      assert(!forall(s, complexPredicate), "Performance with complex predicate")
    }
  }

  // exists - functional test
  test("exists returns true if one element satisfies predicate") {
    new TestSets {
      val s = union(s1, s2)
      assert(exists(s, x => x == 1), "One element satisfies predicate")
    }
  }

  test("exists returns false if no elements satisfy predicate") {
    new TestSets {
      val s = union(s1, s2)
      assert(!exists(s, x => x == 3), "No element satisfies predicate")
    }
  }

  // exists - boundary test
  test("exists handles empty set correctly") {
    assert(!exists(x => false, x => true), "Empty set does not satisfy any predicate")
  }

  test("exists finds element at boundary") {
    val largeSet = (x: Int) => x >= -bound && x <= bound
    assert(exists(largeSet, x => x == bound), "Element exists at upper bound")
  }
    
  // exists - exception handling test
  test("exists handles invalid predicate gracefully") {
    new TestSets {
      val s = s1
      intercept[Exception] {
        exists(s, x => throw new IllegalArgumentException("Invalid Predicate"))
      }
    }
  }

  test("exists handles exception in predicate and returns true") {
    new TestSets {
      val s = union(s1, s2)
      assert(exists(s, x => if (x == 2) throw new Exception("Test exception") else x == 1), "Handles exception and returns true")
    }
  }
    
  // exists - performance test
  test("exists performs efficiently for large set") {
    val largeSet = (x: Int) => x >= -bound && x <= bound
    assert(exists(largeSet, x => x == 0), "Performance test for large set")
  }

  test("exists checks performance with complex predicate") {
    new TestSets {
      val s = union(s1, s2)
      val complexPredicate = (x: Int) => (1 to 1000).exists(n => x % n == 0)
      assert(exists(s, complexPredicate), "Performance with complex predicate")
    }
  }

  // map - functional test
  test("map applies function to set elements") {
    new TestSets {
      val s = union(s1, s2)
      val mapped = map(s, x => x * 2)
      assert(contains(mapped, 2), "Mapped 1 to 2")
      assert(contains(mapped, 4), "Mapped 2 to 4")
    }
  }

  test("map handles identity function correctly") {
    new TestSets {
      val s = union(s1, s2)
      val mapped = map(s, x => x)
      assert(contains(mapped, 1), "Identity map contains 1")
      assert(contains(mapped, 2), "Identity map contains 2")
    }
  }
    
  // map - boundary test
  test("map handles empty set correctly") {
    val mapped = map(x => false, x => x + 1)
    assert(!contains(mapped, 1), "Mapped empty set still empty")
  }

  test("map works with large set and function") {
    val largeSet = (x: Int) => x >= -bound && x <= bound
    val mapped = map(largeSet, x => x + 1)
    assert(contains(mapped, bound), "Mapped last element to bound")
  }
    
  // map - exception handling test
  test("map handles function throwing exception for specific values") {
    new TestSets {
      val s = union(s1, s2)
      val mapped = map(s, x => if (x == 2) throw new Exception("Test exception") else x * 2)
      assert(contains(mapped, 2), "Mapped 1 to 2 despite exception")
    }
  }
    
  // map - performance test
  test("map performs efficiently for large set") {
    new TestSets {
      val largeSet = (x: Int) => x >= -bound && x <= bound
      val mapped = map(largeSet, x => x + 1)
      assert(contains(mapped, bound), "Performance test for large set")
    }
  }

  test("map handles performance with complex mapping function") {
    new TestSets {
      val s = union(s1, s2)
      val complexFunction = (x: Int) => (1 to 1000).map(_ * x).sum
      val mapped = map(s, complexFunction)
      assert(contains(mapped, complexFunction(1)), "Performance with complex mapping function")
    }
  }
}
