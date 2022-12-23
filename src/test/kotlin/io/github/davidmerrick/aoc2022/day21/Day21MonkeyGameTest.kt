package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day21MonkeyGameTest {

    @Test
    fun `Play rounds`() {
        val game = readLines(this::class, "example.txt")
            .map { Day21Monkey.of(it) }
            .let { Day21MonkeyGame(it) }

        game.play() shouldBe 152
    }
}
