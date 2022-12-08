package io.github.davidmerrick.aoc2022.day1

data class Food(val calories: List<Int>) {

    val totalCalories = calories.sum()

    companion object {
        fun parse(a: String) = a.split("\n")
            .map { it.toInt() }
            .let { Food(it) }
    }
}
