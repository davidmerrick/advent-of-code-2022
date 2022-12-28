package io.github.davidmerrick.aoc2022.day25

import kotlin.math.pow

/**
 * Todo: Make this addable to another snafu number
 */
class SnafuNumber(val value: String) {

    fun toDecimal(): Long {
        return value.reversed()
            .mapIndexed { index, c ->
                5.toDouble().pow(index.toDouble()) * charToSnafu(c)
            }.sumOf { it.toLong() }
    }

    private fun charToSnafu(input: Char) = when (input) {
        '-' -> -1
        '=' -> -2
        else -> input.digitToInt().toLong()
    }

    companion object {
        // This is essentially just a base-5 number shifted up by 2
        fun of(value: Long): SnafuNumber = generateSequence(value) { (it + 2) / 5 }
            .takeWhile { it > 0L }
            .map {
                val remainder = (it % 5).toInt()
                "012=-"[remainder]
            }.toList()
            .reversed()
            .joinToString("")
            .let { SnafuNumber(it) }
    }
}