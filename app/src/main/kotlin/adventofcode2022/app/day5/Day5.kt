package adventofcode2022.app.day5

import java.io.File

fun main() {
    task1()
    task2()
}

fun task1() {
    val cargo = crateCargo()
    val file = File({}.javaClass.getResource("/day5/input.txt")?.file ?: throw IllegalStateException())
    file.forEachLine {
        cargo.instruction(it)
    }
    println(cargo.top())
}

private fun Cargo.instruction(instruction: String) {
    if (instruction.isEmpty()) return
    val (amount, source, destination) = "\\d+".toRegex().findAll(instruction).toList().map { it.value.toInt() }
    this.move(amount = amount, from = source, to = destination)
}

fun crateCargo(): Cargo {
    val crates = mapOf(
        1 to listOf(
            'G', 'J', 'Z'
        ),
        2 to listOf(
            'C',
            'V',
            'F',
            'W',
            'P',
            'R',
            'L',
            'Q',
        ),
        3 to listOf(
            'R',
            'G',
            'L',
            'C',
            'M',
            'P',
            'F',
        ),
        4 to listOf(
            'M',
            'H',
            'P',
            'W',
            'B',
            'F',
            'L',
        ),
        5 to listOf(
            'Q',
            'V',
            'S',
            'F',
            'C',
            'G',
        ),
        6 to listOf(
            'L',
            'T',
            'Q',
            'M',
            'Z',
            'J',
            'H',
            'W',

            ),
        7 to listOf(
            'V',
            'B',
            'S',
            'F',
            'H',
        ),

        8 to listOf(
            'S',
            'Z',
            'J',
            'F',
        ),
        9 to listOf(
            'T',
            'B',
            'H',
            'F',
            'P',
            'D',
            'C',
            'M',
        ),
    )
    return Cargo(crates)
}

fun task2() {
    //TODO("Not yet implemented")
}
