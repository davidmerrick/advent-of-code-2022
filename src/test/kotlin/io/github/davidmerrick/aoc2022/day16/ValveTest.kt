package io.github.davidmerrick.aoc2022.day16

import io.kotlintest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class ValveTest {

    @Test
    fun `Parse valve`(){
        val valve = Valve.of("Valve AA has flow rate=0; tunnels lead to valves DD, II, BB")
        valve.id shouldBe "AA"
        valve.flowRate shouldBe 0
        valve.neighbors shouldContainExactlyInAnyOrder listOf("DD", "II", "BB")
    }
}
