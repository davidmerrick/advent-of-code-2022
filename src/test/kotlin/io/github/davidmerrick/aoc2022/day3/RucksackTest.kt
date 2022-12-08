package io.github.davidmerrick.aoc2022.day3

import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class RucksackTest {

    @Test
    fun `Initial test`(){
        val rucksack = Rucksack("vJrwpWtwJgWrhcsFMMfFFhFp")
        rucksack.firstHalf shouldBe "vJrwpWtwJgWr"
        rucksack.secondHalf shouldBe "hcsFMMfFFhFp"
        rucksack.getPriority() shouldBe 16
    }
}
