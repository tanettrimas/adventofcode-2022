package adventofcode2022.app.day6

import java.io.File

fun main() {
    task1()
}

class SubRoutine(private val dataStreamBuffer: String, private val windowSize: Int = defaultWindowSize) {
    companion object {
        private const val defaultWindowSize = 4
    }

    private val streamWindow = dataStreamBuffer.windowed(windowSize)

    fun startOfPacketMarker() =
        dataStreamBuffer.indexOf(streamWindow.find { it.toSet().size == it.length }.orEmpty()) + windowSize
}

fun task1() {
    val file = File({}.javaClass.getResource("/day6/input.txt")?.file ?: throw IllegalStateException())
    val str = file.readText()
    println(SubRoutine(str).startOfPacketMarker())
}