package io.github.davidmerrick.aoc2022.day5

import io.github.davidmerrick.aoc.util.TestUtil

object StackParser {
    fun parse(fileName: String): List<ArrayDeque<Char>> {
        val input = TestUtil.readText(this::class, fileName, false)
            .split("\n\n")
            .first()

        val lines = input
            .lines()
            .filterNot { it.trim().first().isDigit() }

        val stackList = mutableListOf<ArrayDeque<Char>>()

        // Initialize the list
        repeat((0 until columnCount(input)).count()) {
            stackList.add(ArrayDeque())
        }

        // Build the stacks
        for (line in lines) {
            parseLine(line).forEachIndexed { i, value ->
                value?.let { stackList[i].add(it) }
            }
        }

        return stackList
    }

    private fun columnCount(input: String): Int {
        return input.lines()
            .last()
            .last { it.isDigit() }
            .let { it.toString().toInt() }
    }

    private fun parseLine(line: String): List<Char?> {
        var i = 1 // Start at first char position
        return buildList {
            while (i < line.length) {
                val value = line[i]
                if (value.isLetter()) {
                    add(value)
                } else add(null)
                i += 4
            }
        }
    }
}
