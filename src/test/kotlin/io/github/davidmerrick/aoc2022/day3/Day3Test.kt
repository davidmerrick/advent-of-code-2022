package io.github.davidmerrick.aoc2022.day3

import io.github.davidmerrick.aoc.util.TestUtil.lineSequence
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `Part 1 example`() {
        lineSequence(this::class, "example.txt")
            .map { Rucksack(it) }
            .sumOf { it.getPriority() } shouldBe 157
    }

    @Test
    fun `Part 1 full`() {
        lineSequence(this::class, "day3.txt")
            .map { Rucksack(it) }
            .sumOf { it.getPriority() }
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        lineSequence(this::class, "example.txt")
            .chunked(3)
            .map { findCommonChar(it) }
            .map { PriorityResolver.resolve(it) }
            .sumOf { it } shouldBe 70
    }

    @Test
    fun `Part 2 full`() {
        lineSequence(this::class, "day3.txt")
            .chunked(3)
            .map { findCommonChar(it) }
            .map { PriorityResolver.resolve(it) }
            .sumOf { it }
            .let { println(it) }
    }

    private fun findCommonChar(input: List<String>): Char {
        return input
            .map { it.toSet() }
            .reduce { a, b -> a.intersect(b) }
            .first()
    }
}
