package io.github.davidmerrick.aoc2022.day7

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

internal class FileSystemTest {

    @Test
    fun `Part 1 example`() {
        val fileSystem = readLines(this::class, "example.txt")
            .let { FileSystem.of(it) }

        val dirs = fileSystem.let { it.getDirectoriesWithMaxSize(100_000) }

        dirs.size shouldBe 2
        dirs.map { it.first } shouldContainAll listOf("a", "e")
        dirs.sumOf { it.second.size!! } shouldBe 95437
    }

    @Test
    fun `Part 1 full`() {
        val fileSystem = readLines(this::class, "day7.txt")
            .let { FileSystem.of(it) }

        val dirs = fileSystem.let { it.getDirectoriesWithMaxSize(100_000) }
        dirs.let { dirs -> dirs.sumOf { it.second.size!! } }
            .let { println(it) }
    }
}
