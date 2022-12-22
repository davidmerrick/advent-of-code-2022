package io.github.davidmerrick.aoc2022.day21

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class Day21MonkeyTest {

    @Test
    fun `Parse monkey`(){
        val monkey = Day21Monkey.of("cgrb: gzwb * rcfd")
        monkey.id shouldBe "cgrb"
        monkey.operation shouldBe "gzwb * rcfd"
    }
}
