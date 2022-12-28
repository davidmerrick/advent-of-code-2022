package io.github.davidmerrick.aoc2022.day25

import io.kotlintest.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class SnafuNumberTest {

    @TestFactory
    fun `Snafu to int`() = listOf(
        "1" to 1,
        "1=" to 3,
        "1=11-2" to 2022,
        "1-0---0" to 12345,
        "1121-1110-1=0" to 314_159_265,
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Convert Snafu to decimal") {
            SnafuNumber(input).toDecimal() shouldBe expected
        }
    }

    @TestFactory
    fun `Long to snafu`() = listOf(
        1 to "1",
        3 to "1=",
        8 to "2=",
        20 to "1-0",
        2022 to "1=11-2",
        12345 to "1-0---0",
        314_159_265 to "1121-1110-1=0",
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("Convert decimal to snafu") {
            SnafuNumber.of(input.toLong()).value shouldBe expected
        }
    }
}
