package io.github.davidmerrick.aoc2022.day13

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class PacketPairTest {

    @Test
    fun `Pop into test`(){
        "[1,2,3]".popInto() shouldBe "1,2,3"
    }

    @Test
    fun `isList test`(){
        "[1,2,3]".isLeaf() shouldBe false
        "1,2,3".isLeaf() shouldBe true
    }
}
