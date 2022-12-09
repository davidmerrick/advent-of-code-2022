package io.github.davidmerrick.aoc2022.day5

import io.github.davidmerrick.aoc.util.TestUtil

object MoveParser {
    fun parse(fileName: String): List<Move> {
        return TestUtil.readText(this::class, fileName)
            .split("\n\n")
            .last()
            .trim()
            .lines()
            .map { Move.of(it) }
    }
}
