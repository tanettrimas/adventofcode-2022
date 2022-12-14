package adventofcode2022.app.day1

import java.io.File

fun main() {
    println("Oppgave 1: ${Day1().task1()}")
    println("Oppgave 2: ${Day1().task2()}")
}

class Day1 {
    private fun fetchElves(): Elves {
        val urlToFile = this.javaClass.getResource("/day1/input.txt")
        val file = File(urlToFile?.toURI() ?: throw IllegalStateException("No such file $urlToFile"))
        val lines = file.useLines {
            mapToElves(it)
        }
        val elves = Elves(lines)
        return elves
    }

    private fun mapToElves(it: Sequence<String>): List<Elf> {
        var list = mutableListOf<Int>()
        val elvesList = mutableListOf<Elf>()
        for (s in it) {
            if (s.isBlank()) {
                elvesList.add(Elf(list))
                list = mutableListOf()
                continue
            } else {
                list.add(s.toInt())
            }
        }
        return elvesList.toList()
    }

    fun task1(): Int {
        val elves = fetchElves()
        val elfWithMostCalories = elves.mostCalories()
        return elfWithMostCalories.totalCalories()
    }

    fun task2(): Int {
        val elves = fetchElves()
        return elves.topThreeSum()
    }
}