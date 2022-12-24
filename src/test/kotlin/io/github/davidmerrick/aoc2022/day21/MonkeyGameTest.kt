package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class MonkeyGameTest {

    @Test
    fun `Part 1 example`() {
        val game = readLines(this::class, "example.txt")
            .let { MonkeyGame.of(it) }

        game.play() shouldBe 152
    }
}
