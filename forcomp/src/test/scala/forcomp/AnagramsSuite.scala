package forcomp

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import Anagrams._

@RunWith(classOf[JUnitRunner])
class AnagramsSuite extends FunSuite {

  test("wordOccurrences: abcd") {
    assert(wordOccurrences("abcd") === List(('a', 1), ('b', 1), ('c', 1), ('d', 1)))
  }

  test("wordOccurrences: Robert") {
    assert(wordOccurrences("Robert") === List(('b', 1), ('e', 1), ('o', 1), ('r', 2), ('t', 1)))
  }


  test("sentenceOccurrences: abcd e") {
    assert(sentenceOccurrences(List("abcd", "e")) === List(('a', 1), ('b', 1), ('c', 1), ('d', 1), ('e', 1)))
  }


  test("dictionaryByOccurrences.get: eat") {
    assert(dictionaryByOccurrences.get(List(('a', 1), ('e', 1), ('t', 1))).map(_.toSet) === Some(Set("ate", "eat", "tea")))
  }


  test("word anagrams: married") {
    assert(wordAnagrams("married").toSet === Set("married", "admirer"))
  }

  test("word anagrams: player") {
    assert(wordAnagrams("player").toSet === Set("parley", "pearly", "player", "replay"))
  }


  test("subtract: lard - r") {
    val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
    val r = List(('r', 1))
    val lad = List(('a', 1), ('d', 1), ('l', 1))
    assert(subtract(lard, r) === lad)
  }

  test("subtract: afdfdsassdf - ffads") {
    val text1 = wordOccurrences("afdfdsassdf")
    val text2 = wordOccurrences("ffads")
    val result = wordOccurrences("afssdd")
    assert(subtract(text1, text2) === result)
  }


  test("combinations: []") {
    assert(combinations(Nil) === List(Nil))
  }

  test("combinations: abba") {
    val abba = List(('a', 2), ('b', 2))
    val abbacomb = List(
      List(),
      List(('a', 1)),
      List(('a', 2)),
      List(('b', 1)),
      List(('a', 1), ('b', 1)),
      List(('a', 2), ('b', 1)),
      List(('b', 2)),
      List(('a', 1), ('b', 2)),
      List(('a', 2), ('b', 2))
    )
    assert(combinations(abba).toSet === abbacomb.toSet)
  }



  test("sentence anagrams: []") {
    val sentence = List()
    assert(sentenceAnagrams(sentence) === List(Nil))
  }

  test("sentence anagrams: Linux rulez") {
    val sentence = List("Linux", "rulez")
    val anas = List(
      List("Rex", "Lin", "Zulu"),
      List("nil", "Zulu", "Rex"),
      List("Rex", "nil", "Zulu"),
      List("Zulu", "Rex", "Lin"),
      List("null", "Uzi", "Rex"),
      List("Rex", "Zulu", "Lin"),
      List("Uzi", "null", "Rex"),
      List("Rex", "null", "Uzi"),
      List("null", "Rex", "Uzi"),
      List("Lin", "Rex", "Zulu"),
      List("nil", "Rex", "Zulu"),
      List("Rex", "Uzi", "null"),
      List("Rex", "Zulu", "nil"),
      List("Zulu", "Rex", "nil"),
      List("Zulu", "Lin", "Rex"),
      List("Lin", "Zulu", "Rex"),
      List("Uzi", "Rex", "null"),
      List("Zulu", "nil", "Rex"),
      List("rulez", "Linux"),
      List("Linux", "rulez")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  test("sentence anagrams: nilz") {
    val sentence = List("nilz")
    val anas = List()
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  // basic functional test
  test("Basic Functional Test: sentenceOccurrences with simple sentence") {
    assert(sentenceOccurrences(List("hello", "world")) === List(('d', 1), ('e', 1), ('h', 1), ('l', 3), ('o', 2), ('r', 1), ('w', 1)))
  }

  test("Basic Functional Test: wordAnagrams for word 'eat'") {
    assert(wordAnagrams("eat").toSet === Set("ate", "eat", "tea"))
  }

  test("Basic Functional Test: subtract with same occurrences") {
    val occ1 = wordOccurrences("test")
    assert(subtract(occ1, occ1) === List())
  }

  test("Basic Functional Test: combinations with single character") {
    val occ = List(('a', 2))
    val comb = List(List(), List(('a', 1)), List(('a', 2)))
    assert(combinations(occ).toSet === comb.toSet)
  }

  test("Basic Functional Test: sentenceAnagrams for single-word sentence") {
    val sentence = List("apple")
    val anas = List(
      List("apple"),
      List("pep", "Al"),
      List("Al", "pep")
    )
    assert(sentenceAnagrams(sentence).toSet === anas.toSet)
  }

  // boundary test
  test("Boundary Test: sentenceOccurrences with empty list") {
    assert(sentenceOccurrences(List()) === List())
  }

  test("Boundary Test: wordAnagrams for empty string") {
    assert(wordAnagrams("") === List())
  }

  test("Boundary Test: subtract when second list has larger counts") {
    val occ1 = wordOccurrences("a")
    val occ2 = wordOccurrences("aa")
    assert(subtract(occ1, occ2) === List())
  }

  test("Boundary Test: combinations with empty list") {
    assert(combinations(List()) === List(List()))
  }

  test("Boundary Test: sentenceAnagrams with sentence having empty word") {
    val sentence = List("")
    assert(sentenceAnagrams(sentence) === List(List()))
  }

  // exception handling test
  test("Exception Handling Test: subtract with non-overlapping occurrences") {
    val occ1 = wordOccurrences("abc")
    val occ2 = wordOccurrences("xyz")
    assert(subtract(occ1, occ2) === occ1) // occ1 반환해야 함
  }

  test("Exception Handling Test: sentenceOccurrences with non-alphabetic characters") {
    assert(sentenceOccurrences(List("h3ll0")) === List(('0', 1), ('3', 1), ('h', 1), ('l', 2)))
  }

  test("Exception Handling Test: wordAnagrams with special characters") {
    assert(wordAnagrams("@@##!!") === List())
  }

  // performance test
  test("Performance Test: sentenceAnagrams with longer sentence") {
    val sentence = List("scala", "rocks")
    val result = sentenceAnagrams(sentence)
    assert(result.nonEmpty)
  }

  test("Performance Test: combinations with a larger set of occurrences") {
    val occurrences = List(('a', 50), ('b', 50))
    val result = combinations(occurrences)
    assert(result.length > 1000) // 엄청 많은 combination을 만들것이기 때문
  }

  test("Performance Test: sentenceOccurrences with long word list") {
    val sentence = List.fill(100)("test")
    val result = sentenceOccurrences(sentence)
    assert(result.nonEmpty)
  }

  test("Performance Test: wordAnagrams for longer word") {
    val word = "performance"
    val result = wordAnagrams(word)
    assert(result.nonEmpty)
  }
}
