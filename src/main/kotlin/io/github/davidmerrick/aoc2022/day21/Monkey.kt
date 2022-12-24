package io.github.davidmerrick.aoc2022.day21

/**
 * Inspired by Todd Ginsberg's approach here:
 * https://todd.ginsberg.com/post/advent-of-code/2022/day21/
 *
 * I'd solved part 1 using a more bottom-up approach initially, but decided to
 * rewrite because I liked Todd's tree approach better :-).
 *
 * Initially tried brute-forcing part 2 and that was awful.
 * Todd's approach to that was to compute the value for what the side
 * opposite the human should be, then walk the tree downward from the root
 * toward the human with that value and reverse the computations until you
 * land at that leaf node.
 */

const val ROOT_ID = "root"
const val HUMAN_ID = "humn"

interface Monkey {
    val id: String
    fun yell(): Long

    fun humanValue(humanPath: Set<Monkey> = humanPath(), incoming: Long = 0L): Long
    fun humanPath(): Set<Monkey>

    companion object {
        operator fun invoke(row: String): Monkey {
            val id = row.substringBefore(":")
            return if (row.length == 17) {
                OpMonkey(id, row.substring(6..9), row.substringAfterLast(" "), row[11])
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
    override fun humanValue(humanPath: Set<Monkey>, incoming: Long): Long {
        return if (id == HUMAN_ID) incoming else 0
    }

    override fun humanPath() = if (this.id == HUMAN_ID) setOf(this) else emptySet()
}

class OpMonkey(
    override val id: String,
    val leftId: String,
    val rightId: String,
    private val op: Char
) : Monkey {
    lateinit var left: Monkey
    lateinit var right: Monkey

    override fun yell(): Long =
        left.yell() op right.yell()

    override fun humanValue(humanPath: Set<Monkey>, incoming: Long): Long {
        return if (id == ROOT_ID) {
            // Pass in the value of the opposite path to wherever the human is
            if (left in humanPath) {
                left.humanValue(humanPath, right.yell())
            } else right.humanValue(humanPath, left.yell())
        } else if (left in humanPath) {
            left.humanValue(humanPath, incoming leftAntiOp right.yell()) // Negate
        } else {
            right.humanValue(humanPath, incoming rightAntiOp left.yell()) // Negate
        }
    }

    override fun humanPath(): Set<Monkey> {
        val path = left.humanPath() + right.humanPath()
        return if (path.isEmpty()) {
            return emptySet()
        } else setOf(this) + path
    }

    private infix fun Long.op(right: Long): Long =
        when (op) {
            '+' -> this + right
            '-' -> this - right
            '*' -> this * right
            '/' -> this / right
            else -> error(op)
        }

    private infix fun Long.leftAntiOp(right: Long): Long =
        when (op) {
            '+' -> this - right
            '-' -> this + right
            '*' -> this / right
            '/' -> this * right
            else -> error(op)
        }

    private infix fun Long.rightAntiOp(right: Long): Long =
        when (op) {
            '+' -> this - right
            '-' -> right - this
            '*' -> this / right
            '/' -> right / this
            else -> error(op)
        }
}
