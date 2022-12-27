package io.github.davidmerrick.aoc2022.day25

import io.kotlintest.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class SnafuNumberTest {

    @TestFactory
    fun `Part 1 examples`() = listOf(
        "1" to 1,
        "1=" to 3,
        "1=11-2" to 2022,
        "1-0---0" to 12345,
        "1121-1110-1=0" to 314_159_265,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Convert Snafu to decimal") {
            SnafuNumber(input).toInt() shouldBe expected
        }
    }


}
