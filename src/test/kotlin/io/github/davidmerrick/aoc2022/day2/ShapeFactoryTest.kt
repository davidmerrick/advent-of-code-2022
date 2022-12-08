package io.github.davidmerrick.aoc2022.day2

import io.kotlintest.matchers.instanceOf
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class ShapeFactoryTest {


    @Test
    fun `Should correctly resolve shapes by alias`() {
        ShapeResolver.resolve('A') shouldBe instanceOf(Rock::class)
        ShapeResolver.resolve('B') shouldBe instanceOf(Paper::class)
        ShapeResolver.resolve('C') shouldBe instanceOf(Scissors::class)
    }
}
