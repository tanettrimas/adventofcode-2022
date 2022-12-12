package adventofcode2022.app.day7

import java.io.File

fun main() {
    task1()
}

fun task1() {
    val file = File({}.javaClass.getResource("/day7/input.txt")?.file ?: throw IllegalStateException())
    val fileSystem = FileSystem()
    file.forEachLine { it.interpretCommand(fileSystem) }
    val maxTotalSize: Long = 100000
    fileSystem.moveToRoot()
    println(fileSystem.findTotalWithCapacity(maxTotalSize))
}

private fun String.interpretCommand(fileSystem: FileSystem) {
    val commands = this.split(" ")
    command(commands, fileSystem)
}

private fun command(commands: List<String>, fileSystem: FileSystem) {
    val isExecutable = commands[0] == "$"
    if (isExecutable) {
        val command = commands[1]
        if (command == "ls") {
            return
        }
        val name = commands[2]
        if (command == "cd") {
            fileSystem.moveTo(name)
            return
        }
    } else {
        val command = commands[0]
        val name = commands[1]
        if (command == "dir") {
            fileSystem.createDirectory(name)
            return
        }
        val fileSize = command.toLongOrNull()
        if (fileSize != null) {
            fileSystem.createFile(name, fileSize)
            return
        }
    }
}