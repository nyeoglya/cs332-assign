package objsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class TweetSetSuite extends FunSuite {
  trait TestSets {
    val set1 = new Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  def asSet(tweets: TweetSet): Set[Tweet] = {
    var res = Set[Tweet]()
    tweets.foreach(res += _)
    res
  }

  def size(set: TweetSet): Int = asSet(set).size

  test("filter: on empty set") {
    new TestSets {
      assert(size(set1.filter(tw => tw.user == "a")) === 0)
    }
  }

  test("filter: a on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.user == "a")) === 1)
    }
  }

  test("filter: 20 on set5") {
    new TestSets {
      assert(size(set5.filter(tw => tw.retweets == 20)) === 2)
    }
  }

  test("union: set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  test("union: with empty set (1)") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
    }
  }

  test("union: with empty set (2)") {
    new TestSets {
      assert(size(set1.union(set5)) === 4)
    }
  }

  test("descending: set5") {
    new TestSets {
      val trends = set5.descendingByRetweet
      assert(!trends.isEmpty)
      assert(trends.head.user == "a" || trends.head.user == "b")
    }
  }

  // filter - basic functional test
  test("filter: tweets with more than 15 likes") {
    new TestSets {
      val filtered = set5.filter(t => t.retweets > 15)
      assert(size(filtered) === 2)
    }
  }

  // filter - boundary test
  test("filter: empty set returns empty set") {
    new TestSets {
      assert(size(set1.filter(t => t.retweets > 15)) === 0)
    }
  }

  // filter - exception handling test
  test("filter: tweets with invalid condition (null check)") {
    new TestSets {
      intercept[NullPointerException] {
        set5.filter(null)
      }
    }
  }

  // filterAcc - basic functional test
  test("filterAcc: filter tweets with exactly 20 retweets") {
    new TestSets {
      val filtered = set5.filterAcc(t => t.retweets == 20, new Empty)
      assert(size(filtered) === 2)
    }
  }

  // filterAcc - boundary test
  test("filterAcc: empty accumulator should return correct filtered set") {
    new TestSets {
      val filtered = set1.filterAcc(t => t.retweets > 10, new Empty)
      assert(size(filtered) === 0)
    }
  }

  // filterAcc - exception handling test
  test("filterAcc: handling null predicate") {
    new TestSets {
      intercept[NullPointerException] {
        set5.filterAcc(null, new Empty)
      }
    }
  }

  // union - basic functional test
  test("union: combine set4c and set4d") {
    new TestSets {
      assert(size(set4c.union(set4d)) === 4)
    }
  }

  // union - boundary test
  test("union: union with empty set should return original set") {
    new TestSets {
      assert(size(set5.union(set1)) === 4)
      assert(size(set1.union(set5)) === 4)
    }
  }

  // union - exception handling test
  test("union: null set should throw Error") {
    new TestSets {
      intercept[Throwable] {
        set5.union(null)
      }
    }
  }

  // INFO: performance test는 sbt run으로 대체
}
