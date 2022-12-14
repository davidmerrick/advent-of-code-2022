package io.github.davidmerrick.aoc2022.day13

import io.github.davidmerrick.aoc.collections.filterNotEmpty
import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.github.davidmerrick.aoc.util.product
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val DECODER_ONE = "[[2]]"
private const val DECODER_TWO = "[[6]]"

class Day13Test {

    @Test
    fun `Part 1 example`() {
        readText(this::class, "example.txt")
            .split("\n\n")
            .map { it.split("\n") }
            .mapIndexed { i, str -> i + 1 to PacketPair.of(str).isInOrder() }
            .filter { it.second }
            .sumOf { it.first } shouldBe 13
    }

    @Test
    fun `Part 1`() {
        readText(this::class, "day13.txt")
            .split("\n\n")
            .map { it.split("\n") }
            .mapIndexed { i, str -> i + 1 to PacketPair.of(str).isInOrder() }
            .filter { it.second }
            .sumOf { it.first }
            .let { println(it) }
    }

    @Test
    fun `Part 2 example`() {
        readText(this::class, "example.txt")
            .split("\n")
            .filterNotEmpty()
            .apply {
                add(DECODER_ONE)
                add(DECODER_TWO)
            }
            .sortedWith(PacketComparator)
            .mapIndexed { i, value -> i + 1 to value }
            .filter { it.second == DECODER_ONE || it.second == DECODER_TWO }
            .map { it.first }
            .product() shouldBe 140
    }

    @Test
    fun `Part 2 full`() {
        readText(this::class, "day13.txt")
            .split("\n")
            .filterNotEmpty()
            .apply {
                add(DECODER_ONE)
                add(DECODER_TWO)
            }
            .sortedWith(PacketComparator)
            .mapIndexed { i, value -> i + 1 to value }
            .filter { it.second == DECODER_ONE || it.second == DECODER_TWO }
            .map { it.first }
            .product()
            .let { println(it) }
    }
}
