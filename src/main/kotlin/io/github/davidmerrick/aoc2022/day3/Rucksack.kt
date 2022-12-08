package io.github.davidmerrick.aoc2022.day3

data class Rucksack(private val value: String) {
    val firstHalf = value.take(value.length / 2)
    val secondHalf = value.takeLast(value.length / 2)

    fun getPriority(): Int {
        return firstHalf.toSet()
            .intersect(secondHalf.toSet())
            .first()
            .let { PriorityResolver.resolve(it) }
    }

}

object PriorityResolver {
    private val priorities = (('a'..'z') + ('A'..'Z'))
        .mapIndexed { i, value -> value to i + 1 }
        .toMap()

    fun resolve(a: Char) = priorities[a]!!
}
