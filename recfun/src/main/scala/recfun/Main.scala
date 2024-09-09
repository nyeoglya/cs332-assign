package recfun
import common._

object Main {
    def main(args: Array[String]) = {
        println("Pascal's Triangle")
        for (row <- 0 to 10) {
            for (col <- 0 to row) print(pascal(col, row) + " ")
            println()
        }
    }

    /**
     * Exercise 1
     */
    def pascal(c: Int, r: Int): Int = {
        if (c == 0 || c == r) 1
        else pascal(c, r-1) + pascal(c-1, r-1)
    }

    /**
     * Exercise 2
     */
    def balance(chars: List[Char]): Boolean = {
        def innerRecursive(chars: List[Char], openedParenthesesCount: Int): Boolean = chars match {
            case Nil => openedParenthesesCount == 0
            case '(' +: tail => innerRecursive(tail, openedParenthesesCount + 1)
            case ')' +: tail => openedParenthesesCount > 0 && innerRecursive(tail, openedParenthesesCount - 1)
            case _ +: tail => innerRecursive(tail, openedParenthesesCount)
        }
        innerRecursive(chars, 0)
    }


    /**
     * Exercise 3
     */
    def countChange(money: Int, coins: List[Int]): Int = {
        if (money == 0) 1
        else if (money < 0 || coins.isEmpty) 0
        else countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
}
