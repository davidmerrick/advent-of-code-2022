package io.github.davidmerrick.aoc2022.day13

import io.github.davidmerrick.aoc.util.TestUtil.readText
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun `Parse packet pairs`() {
        val packets = readText(this::class, "example.txt")
            .split("\n\n")
            .map { it.split("\n") }
            .mapIndexed { i, str -> i + 1 to PacketPair.of(str) }
            .toMap()

        packets.size shouldBe 8
    }
}
