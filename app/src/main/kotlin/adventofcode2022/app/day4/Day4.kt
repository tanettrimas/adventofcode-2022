package adventofcode2022.app.day4

import java.io.File

fun main() {
    task1()
}

fun task1() {
    val file = File({}.javaClass.getResource("/day4/input.txt")?.file ?: throw IllegalStateException())
    val count = file.useLines {
        it.filter { entry ->
            val (first, second) = entry.split(",")
            val firstPair = first.split("-").map(String::toInt)
            val secondPair = second.split("-").map(String::toInt)
            ElfPair(firstPair[0], firstPair[1]).containsAll(ElfPair(secondPair[0], secondPair[1]))
        }.count()
    }
    println(count)
}
