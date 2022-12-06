package adventofcode2022.app.day3

import java.io.File

fun main() {
    task1()
    task2()
}

fun task1() {
    val file = File({}.javaClass.getResource("/day3/input.txt")?.file ?: throw IllegalStateException())
    val priorities = file.useLines { it.map(::SingleRucksack).sumOf(Rucksack::priorities) }
    println(priorities)
}

fun task2() {
    val file = File({}.javaClass.getResource("/day3/input.txt")?.file ?: throw IllegalStateException())
    val priorities = file.useLines {
        it.chunked(3)
            .sumOf { list -> GroupedRucksack(list.joinToString(separator = "\n")).priorities() }
    }
    println(priorities)
}

