package io.github.davidmerrick.aoc2022.day21

/**
 * Inspired by Todd Ginsberg's approach here:
 * https://todd.ginsberg.com/post/advent-of-code/2022/day21/
 *
 * I'd solved this using a more bottom-up approach initially, but decided to
 * rewrite because I liked Todd's tree approach better :-).
 */

interface Monkey {
    val id: String
    fun yell(): Long

    companion object {
        operator fun invoke(row: String): Monkey {
            val id = row.substringBefore(":")
            return if (row.length == 17) {
                FormulaMonkey(id, row.substring(6..9), row.substringAfterLast(" "), row[11])
            } else {
                NumberMonkey(id, row.substringAfter(" ").toLong())
            }
        }
    }
}

class NumberMonkey(
    override val id: String,
    private val value: Long
) : Monkey {
    override fun yell(): Long = value
}

class FormulaMonkey(
    override val id: String,
    val leftId: String,
    val rightId: String,
    private val op: Char
) : Monkey {
    lateinit var left: Monkey
    lateinit var right: Monkey

    override fun yell(): Long =
        left.yell() op right.yell()

    private infix fun Long.op(right: Long): Long =
        when (op) {
            '+' -> this + right
            '-' -> this - right
            '*' -> this * right
            '/' -> this / right
            else -> error(op)
        }
}
