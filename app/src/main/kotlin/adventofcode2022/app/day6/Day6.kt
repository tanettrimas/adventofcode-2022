package adventofcode2022.app.day6

import java.io.File

fun main() {
    task1()
    task2()
}

fun task1() {
    val file = File({}.javaClass.getResource("/day6/input.txt")?.file ?: throw IllegalStateException())
    val str = file.readText()
    println(SubRoutine(str).startOfPacketMarker())
}

fun task2() {
    val windowSize = 14
    val file = File({}.javaClass.getResource("/day6/input.txt")?.file ?: throw IllegalStateException())
    val str = file.readText()
    println(SubRoutine(str, windowSize).startOfPacketMarker())
}