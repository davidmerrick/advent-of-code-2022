package io.github.davidmerrick.aoc2022.day21

import io.github.davidmerrick.aoc.util.TestUtil
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun `Part 1`() {
        TestUtil.readLines(this::class, "day21.txt")
            .let { MonkeyGame.of(it) }
            .play() shouldBe 158_731_561_459_602L
    }
}
