package io.github.davidmerrick.aoc2022.day20

private const val DECRYPTION_KEY = 811_589_153L

fun List<Int>.toMappedInts(): MutableList<IndexedNumber> {
    return this.mapIndexed { index, value -> IndexedNumber(index, value.toLong()) }.toMutableList()
}

fun MutableList<IndexedNumber>.mix() {
    indices.forEach { originalIndex ->
        val index = indexOfFirst { it.originalIndex == originalIndex }
        val toMove = removeAt(index)
        val newIndex = (index + toMove.value).mod(size)
        add(newIndex, toMove)
    }
}

fun MutableList<IndexedNumber>.mixPart2() {
    this.forEachIndexed { index, item ->
        val newItem = item.copy(value = item.value * DECRYPTION_KEY)
        this[index] = newItem
    }
    repeat(10) {
        indices.forEach { originalIndex ->
            val index = indexOfFirst { it.originalIndex == originalIndex }
            val toMove = removeAt(index)
            val newIndex = (index + toMove.value).mod(size)
            add(newIndex, toMove)
        }
    }
}

fun MutableList<IndexedNumber>.groveCoordinates(): List<Long> {
    val zeroIndex = this.indexOfFirst { it.value == 0L }
    return listOf(1000, 2000, 3000)
        .map { this[(zeroIndex + it).mod(size)] }
        .map { it.value }
}
