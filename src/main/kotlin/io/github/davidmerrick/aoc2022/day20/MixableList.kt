package io.github.davidmerrick.aoc2022.day20

class MixableList(input: List<Int>) {
    private val values = input.mapIndexed { index, value -> MappedInt(index, value) }

    fun mix(): List<Int> {
        var newList = values.toMutableList()
        for (item in values) {
            newList = move(newList, newList.indexOfFirst { it.originalIndex == item.originalIndex })
        }
        return newList.map { it.value }
    }

    private fun boundIndex(index: Int): Int {
        val bounded = index % values.size
        return if (bounded < 0) values.size + bounded else bounded
    }

    private fun move(input: MutableList<MappedInt>, index: Int): MutableList<MappedInt> {
        val value = input.removeAt(index)
        val newIndex = computeNewIndex(index, input.size, value.value)
        input.add(newIndex, value)
        return input
    }

    private fun computeNewIndex(index: Int, listSize: Int, value: Int): Int {
        return boundIndex((index + value) % listSize)
    }
}
