package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {
  import Main.balance

  // example
  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }

  test("balance: 'I told him ...' is balanced") {
    assert(balance("I told him (that it's not (yet) done).\n(But he wasn't listening)".toList))
  }

  test("balance: ':-)' is unbalanced") {
    assert(!balance(":-)".toList))
  }

  test("balance: counting is not enough") {
    assert(!balance("())(".toList))
  }

  // basic functionality test
  test("balance: basic functionality 1") {
    assert(balance("()".toList))
  }

  test("balance: basic functionality 2") {
    assert(balance("(())".toList))
  }

  test("balance: basic functionality 3") {
    assert(balance("()()".toList))
  }

  test("balance: basic functionality 4") {
    assert(balance("(())()".toList))
  }

  test("balance: basic functionality 5") {
    assert(!balance("(()))".toList)) // 잘못된 괄호
  }

  // boundary test
  test("balance: boundary test 1") {
    assert(balance("".toList)) // 빈 문자열
  }

  test("balance: boundary test 2") {
    assert(!balance("(".toList)) // 열린 괄호
  }

  test("balance: boundary test 3") {
    assert(!balance(")".toList)) // 닫힌 괄호
  }

  test("balance: boundary test 4") {
    assert(!balance("))".toList)) // 닫힌 괄호 x 2
  }

  test("balance: boundary test 5") {
    assert(!balance("((".toList)) // 열린 괄호 x 2
  }

  // exception test
  test("balance: exception handling 1") {
    assert(balance("a(b)c".toList)) // 문자 포함한 괄호
  }

  test("balance: exception handling 2") {
    assert(!balance("(a(b)c)d)".toList)) // 문자 포함된 괄호
  }

  test("balance: exception handling 3") {
    assert(balance("(abc)".toList)) // 괄호 안 여러 문자
  }

  test("balance: exception handling 4") {
    assert(!balance(")(abc(".toList)) // 잘못된 순서의 괄호
  }

  test("balance: exception handling 5") {
    assert(balance("((a)b)c".toList)) // 괄호와 문자 혼합
  }

  // performance test
  test("balance: performance test 1") {
    val largeBalanced = "(" * 10000 + ")" * 10000 // 큰 균형잡힌 괄호
    assert(balance(largeBalanced.toList))
  }

  test("balance: performance test 2") {
    val largeBalanced = "(" * 10000 + ")" * 9999 // 큰 불균형인 괄호
    assert(!balance(largeBalanced.toList))
  }

  test("balance: performance test 3") {
    val largeBalanced = "(" + "a" * 9998 + ")" * 10000 // 문자 있고 큰 불균형
    assert(!balance(largeBalanced.toList))
  }

  test("balance: performance test 4") {
    val largeBalanced = "a" * 10000 // 괄호 없이 문자만 큰 균형
    assert(balance(largeBalanced.toList))
  }

  test("balance: performance test 5") {
    val largeBalanced = "(" * 5000 + ")" * 5000 + "a" * 5000 + "(" * 5000 + ")" * 4999 // 복잡하게 섞인 큰 괄호
    assert(!balance(largeBalanced.toList))
  }
}
