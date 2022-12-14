package io.github.davidmerrick.aoc2022.day13

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

class PacketPairTest {

    @Test
    fun `check ordering`(){
        val lines = """
            [[1],[2,3,4]]
            [[1],4]
        """.trimIndent()
            .lines()
        PacketPair.of(lines).isInOrder() shouldBe true
    }

    @Test
    fun `check ordering 2`(){
        val lines = """
            [[4,4],4,4]
            [[4,4],4,4,4]
        """.trimIndent()
            .lines()
        PacketPair.of(lines).isInOrder() shouldBe true
    }

    @Test
    fun `check ordering 3`(){
        val lines = """
            [[[]]]
            [[]]
        """.trimIndent()
            .lines()
        PacketPair.of(lines).isInOrder() shouldBe false
    }
}
