package io.github.davidmerrick.aoc2022.day3

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class PriorityResolverTest {

    @Test
    fun `Resolve priorities`(){
        PriorityResolver.resolve('a') shouldBe 1
        PriorityResolver.resolve('A') shouldBe 27
        PriorityResolver.resolve('Z') shouldBe 52
    }
}
