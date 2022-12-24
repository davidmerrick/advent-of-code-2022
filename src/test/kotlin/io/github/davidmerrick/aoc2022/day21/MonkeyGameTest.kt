package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class MonkeyGameTest {

    @Test
    fun `Part 1 example`() {
        readLines(this::class, "example.txt")
            .let { MonkeyGame.of(it) }
            .play() shouldBe 152
    }

    @Test
    fun `Part 2 example`() {
        readLines(this::class, "example.txt")
            .let { MonkeyGame.of(it) }
            .humanValue() shouldBe 301
    }

    @Test
    fun `Part 2`() {
        readLines(this::class, "day21.txt")
            .let { MonkeyGame.of(it) }
            .humanValue() shouldBe 3_769_668_716_709
    }
}
