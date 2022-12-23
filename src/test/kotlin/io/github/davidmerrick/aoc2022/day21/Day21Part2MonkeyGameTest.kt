package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day21Part2MonkeyGameTest {

    @Test
    fun `Check value`(){
        val game = readLines(this::class, "example.txt")
            .map { Day21Monkey.of(it) }
            .let { Day21Part2MonkeyGame(it) }

        game.checkValue(301) shouldBe true
        game.checkValue(1) shouldBe false
    }

    @Test
    fun `Find input`(){
        val game = readLines(this::class, "example.txt")
            .map { Day21Monkey.of(it) }
            .let { Day21Part2MonkeyGame(it) }

        game.findInput() shouldBe 301
    }
}
