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
        fun of(decimal: Int) {
            TODO()
        }
    }
}

fun Char.fromSnafu() = when (this) {
    '-' -> -1
    '=' -> -2
    else -> this.digitToInt()
}
