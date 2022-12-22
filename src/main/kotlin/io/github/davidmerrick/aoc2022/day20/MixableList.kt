package io.github.davidmerrick.aoc2022.day20

class MixableList(private val values: List<Int>) {
    fun mix(): List<Int> {
        var newList = values.toMutableList()
        for (item in values) {
            newList = move(newList, newList.indexOf(item))
        }
        return newList
    }

    private fun boundIndex(index: Int): Int {
        val bounded = index % values.size
        return if (bounded < 0) values.size + bounded else bounded
    }

    private fun move(input: MutableList<Int>, index: Int): MutableList<Int> {
        val value = input.removeAt(index)
        val newIndex = computeNewIndex(index, input.size, value)
        input.add(newIndex, value)
        return input
    }

    private fun computeNewIndex(index: Int, listSize: Int, value: Int): Int {
        return boundIndex((index + value) % listSize)
    }
}
