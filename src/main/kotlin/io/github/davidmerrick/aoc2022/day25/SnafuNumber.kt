package io.github.davidmerrick.aoc2022.day25

import kotlin.math.pow

/**
 * Todo: Make this addable to another snafu number
 */
class SnafuNumber(val value: String) {

    fun toInt(): Long {
        return value.reversed()
            .mapIndexed { index, c ->
                (5.toDouble().pow(index.toDouble()) * c.fromSnafu()).toLong()
            }.sum()
    }
}

fun Char.fromSnafu() = when (this) {
    '-' -> -1
    '=' -> -2
    else -> this.digitToInt()
}

fun Long.toSnafu(): String {
    return generateSequence(this) { (it + 2) / 5 }
        .takeWhile { it > 0L }
        .map {
            val remainder = (it % 5).toInt()
            when (remainder) {
                0 -> "0"
                1 -> "1"
                2 -> "2"
                3 -> "="
                4 -> "-"
                else -> error(remainder)
            }
        }.toList()
        .reversed().joinToString("")
}
