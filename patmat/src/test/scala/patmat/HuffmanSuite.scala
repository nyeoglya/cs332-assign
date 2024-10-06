package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode a text should be identity") {
    new TestTrees {
      val text = "huffmanestcool"
      assert(decode(frenchCode, quickEncode(frenchCode)(text.toList)) === text.toList)
    }
  }

  // weight - Basic Functional Test
test("weight - Basic Functional Test: weight of a tree with two leaves") {
  val t = Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3)
  assert(weight(t) === 3)
}

// weight - Boundary Test
test("weight - Boundary Test: weight of a tree with a single leaf") {
  val t = Leaf('a', 5)
  assert(weight(t) === 5)
}

// weight - Exception Handling Test
test("weight - Exception Handling Test: empty tree") {
  intercept[scala.MatchError] {
    weight(null)
  }
}

// chars - Basic Functional Test
test("chars - Basic Functional Test: characters of a simple tree") {
  val t = Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3)
  assert(chars(t) === List('a', 'b'))
}

// chars - Boundary Test
test("chars - Boundary Test: character of a single leaf") {
  val t = Leaf('c', 4)
  assert(chars(t) === List('c'))
}

// chars - Exception Handling Test
test("chars - Exception Handling Test: empty tree") {
  intercept[scala.MatchError] {
    chars(null)
  }
}

// makeCodeTree - Basic Functional Test
test("makeCodeTree - Basic Functional Test: combining two leaf nodes") {
  val left = Leaf('a', 2)
  val right = Leaf('b', 3)
  val combined = makeCodeTree(left, right)
  assert(combined === Fork(left, right, List('a', 'b'), 5))
}

// makeCodeTree - Boundary Test
test("makeCodeTree - Boundary Test: combining identical leaf nodes") {
  val left = Leaf('a', 1)
  val right = Leaf('a', 1)
  val combined = makeCodeTree(left, right)
  assert(combined === Fork(left, right, List('a', 'a'), 2))
}

// times - Basic Functional Test
test("times - Basic Functional Test: frequency of characters in a list") {
  val charList = List('a', 'b', 'a', 'c', 'a')
  assert(times(charList).toSet === Set(('a', 3), ('b', 1), ('c', 1)))
}

// times - Boundary Test
test("times - Boundary Test: single character list") {
  val charList = List('a')
  assert(times(charList) === List(('a', 1)))
}

// times - Exception Handling Test
test("times - Exception Handling Test: empty list") {
  assert(times(Nil) === Nil)
}

// makeOrderedLeafList - Basic Functional Test
test("makeOrderedLeafList - Basic Functional Test: order by frequency") {
  val freqList = List(('a', 3), ('b', 1), ('c', 2))
  assert(makeOrderedLeafList(freqList) === List(Leaf('b', 1), Leaf('c', 2), Leaf('a', 3)))
}

// makeOrderedLeafList - Boundary Test
test("makeOrderedLeafList - Boundary Test: single element list") {
  val freqList = List(('a', 1))
  assert(makeOrderedLeafList(freqList) === List(Leaf('a', 1)))
}

// makeOrderedLeafList - Exception Handling Test
test("makeOrderedLeafList - Exception Handling Test: empty list") {
  assert(makeOrderedLeafList(Nil) === Nil)
}

// combine - Basic Functional Test
test("combine - Basic Functional Test: combining a leaf list") {
  val leafList = List(Leaf('a', 1), Leaf('b', 2))
  assert(combine(leafList) === List(Fork(Leaf('a', 1), Leaf('b', 2), List('a', 'b'), 3)))
}

// combine - Boundary Test
test("combine - Boundary Test: single leaf list") {
  val leafList = List(Leaf('a', 1))
  assert(combine(leafList) === List(Leaf('a', 1)))
}

// combine - Exception Handling Test
test("combine - Exception Handling Test: empty list") {
  assert(combine(Nil) === Nil)
}

// decode - Basic Functional Test
test("decode - Basic Functional Test: decode a simple encoded message") {
  new TestTrees {
    assert(decode(t1, List(0, 1)) === "ab".toList)
  }
}

// decode - Boundary Test
test("decode - Boundary Test: decode with no bits") {
  new TestTrees {
    assert(decode(t1, Nil) === Nil)
  }
}

// encode - Basic Functional Test
test("encode - Basic Functional Test: encode a simple string") {
  new TestTrees {
    assert(encode(t1)("ab".toList) === List(0, 1))
  }
}

// encode - Boundary Test
test("encode - Boundary Test: empty string") {
  new TestTrees {
    assert(encode(t1)(Nil) === Nil)
  }
}

// createCodeTree - Boundary Test
test("createCodeTree - Boundary Test: single character list") {
  val charList = List('a')
  val tree = createCodeTree(charList)
  assert(chars(tree) === List('a'))
}

// createCodeTree - Exception Handling Test
test("createCodeTree - Exception Handling Test: empty character list") {
  intercept[IllegalArgumentException] {
    createCodeTree(Nil)
  }
}

}
