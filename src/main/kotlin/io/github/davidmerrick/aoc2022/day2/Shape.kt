package io.github.davidmerrick.aoc2022.day2

import kotlin.reflect.KClass

open class Shape(val aliases: List<Char>, val value: Int, val winsAgainst: KClass<out Shape>)
object Paper : Shape(listOf('B', 'Y'), 2, Rock::class)
object Rock : Shape(listOf('A', 'X'), 1, Scissors::class)
object Scissors : Shape(listOf('C', 'Z'), 3, Paper::class)
