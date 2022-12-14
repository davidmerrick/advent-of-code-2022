package io.github.davidmerrick.aoc2022.day13

import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class PacketComparatorTest {

    @Test
    fun `Trim brackets`() {
        "[1,2,3]".trimBrackets() shouldBe "1,2,3"
    }

    @Test
    fun `isList test`() {
        "[1,2,3]".isList() shouldBe true
        "1,2,3".isList() shouldBe false
    }

    @Test
    fun `splitList test`() {
        PacketComparator.splitList("[1,2,3]") shouldContainAll listOf("1", "2", "3")
        PacketComparator.splitList("[[],[2,3]]") shouldContainAll listOf("[]", "[2,3]")
    }

    @Test
    fun `splitList test with double digits`() {
        PacketComparator.splitList("[10,2,3]") shouldContainAll listOf("10", "2", "3")
    }
}
