package io.github.davidmerrick.aoc2022.day7

import io.github.davidmerrick.aoc.util.TestUtil.readLines
import io.kotlintest.matchers.collections.shouldContainAll
import io.kotlintest.shouldBe
import org.junit.jupiter.api.Test

private const val FILESYSTEM_SIZE = 70_000_000
private const val UPDATE_SIZE = 30_000_000

internal class Day7Test {

    @Test
    fun `Part 1 example`() {
        val fileSystem = readLines(this::class, "example.txt")
            .let { FileSystem.of(it) }

        val dirs = fileSystem.let { it.getDirectoriesWithMaxSize(100_000) }

        dirs.size shouldBe 2
        dirs.map { it.first } shouldContainAll listOf("/a", "/a/e")
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

    @Test
    fun `Part 2 example`() {
        val fileSystem = readLines(this::class, "example.txt")
            .let { FileSystem.of(it) }

        val totalUsed = fileSystem.totalUsed()
        totalUsed shouldBe 48_381_165

        val needFreed = UPDATE_SIZE - (FILESYSTEM_SIZE - totalUsed)
        fileSystem.getDirectoriesBy { it.size!! > needFreed }
            .minOfOrNull { it.second.size!! } shouldBe 24_933_642
    }

    @Test
    fun `Part 2 full`() {
        val fileSystem = readLines(this::class, "day7.txt")
            .let { FileSystem.of(it) }

        val needFreed = UPDATE_SIZE - (FILESYSTEM_SIZE - fileSystem.totalUsed())
        fileSystem.getDirectoriesBy { it.size!! > needFreed }
            .minOfOrNull { it.second.size!! }
            .let { println(it) }
    }
}
