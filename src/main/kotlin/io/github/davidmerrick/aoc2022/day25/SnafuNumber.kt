package io.github.davidmerrick.aoc2022.day25

import kotlin.math.pow

/**
 * Todo: Make this addable to another snafu number
 */
class SnafuNumber(val value: String) {

    fun toInt(): Int {
        return value.reversed()
            .mapIndexed { index, c ->
                (5.toDouble().pow(index.toDouble()) * c.fromSnafu()).toInt()
            }.sum()
    }

    companion object {
        fun of(decimal: Int): SnafuNumber {
            return buildList {
                var value = decimal
                while (value > 0) {
                    this.add((value % 5).remanderToSnafu())
                    value /= 5
                }
                this.reversed()
            }.joinToString("")
                .let { SnafuNumber(it) }
        }
    }
}

fun Char.fromSnafu() = when (this) {
    '-' -> -1
    '=' -> -2
    else -> this.digitToInt()
}

fun Int.remanderToSnafu() = when (this) {
    0 -> "0"
    1 -> "1"
    2 -> "2"
    3 -> "1="
    4 -> "1-"
    else -> error(this)
}