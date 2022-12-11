package io.github.davidmerrick.aoc2022.day11

import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class MonkeyTest {

    @Test
    fun `Parse monkey, that funky monkey`() {
        val monkey = Monkey.of(
            """
                Monkey 1:
                Starting items: 54, 65, 75, 74
                Operation: new = old + 6
                Test: divisible by 19
                  If true: throw to monkey 2
                  If false: throw to monkey 0
            """.trimIndent()
        )
        monkey.id shouldBe 1
        monkey.items shouldContainAll listOf(54, 65, 75, 74)
        monkey.test shouldBe Test(19, 2, 0)
    }
}
