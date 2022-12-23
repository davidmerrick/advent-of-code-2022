package io.github.davidmerrick.aoc2022.day21

interface Monkey {
    val id: String
    fun yell(): Long

    companion object {
        operator fun invoke(row: String): Monkey {
            val name = row.substringBefore(":")
            return if (row.length == 17) {
                FormulaMonkey(name, row.substring(6..9), row.substringAfterLast(" "), row[11])
            } else {
                NumberMonkey(name, row.substringAfter(" ").toLong())
            }
        }
    }
}

class NumberMonkey(
    override val id: String,
    private val number: Long
) : Monkey {
    override fun yell(): Long = number
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
            else -> this / right
        }
}
