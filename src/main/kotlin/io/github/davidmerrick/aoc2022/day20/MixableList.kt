package io.github.davidmerrick.aoc2022.day20

fun List<Int>.toMappedInts(): MutableList<IndexedNumber> {
    return this.mapIndexed { index, value -> IndexedNumber(index, value.toLong()) }.toMutableList()
}

fun MutableList<IndexedNumber>.mix() {
    indices.forEach { originalIndex ->
        val index = indexOfFirst { it.originalIndex == originalIndex }
        val toMove = removeAt(index)
        add((index + toMove.value).mod(size), toMove)
    }
}

fun MutableList<IndexedNumber>.groveCoordinates(): List<Long> {
    val zeroIndex = this.indexOfFirst { it.value == 0L }
    return listOf(1000, 2000, 3000)
        .map { this[(zeroIndex + it).mod(size)] }
        .map { it.value }
}
