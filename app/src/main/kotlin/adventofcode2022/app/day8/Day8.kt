package adventofcode2022.app.day8

import java.io.File

fun main() {
    task1()
    task2()
}

fun task2() {
    val file = File({}.javaClass.getResource("/day8/input.txt")?.file ?: throw IllegalStateException())
    val treeStructure = TreeStructure(file.readText())
    println(treeStructure.scienicScore())
}

fun task1() {
    val file = File({}.javaClass.getResource("/day8/input.txt")?.file ?: throw IllegalStateException())
    val treeStructure = TreeStructure(file.readText())
    println(treeStructure.visible())
}

