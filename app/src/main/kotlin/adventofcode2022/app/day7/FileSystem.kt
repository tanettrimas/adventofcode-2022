package adventofcode2022.app.day7

import java.nio.file.NotDirectoryException

private const val ROOT_DIRECTORY_NAME = "/"
private const val MOVE_UP_NAME = ".."

class FileSystem {
    private val ROOT_DIRECTORY: Directory = Directory(parent = null, name = ROOT_DIRECTORY_NAME)
    private var currentWorkingDirectory: Directory = ROOT_DIRECTORY

    fun hasDirectory(name: String): Boolean {
        if (name == ROOT_DIRECTORY_NAME) {
            return true
        }
        return currentWorkingDirectory.hasDirectory(name = name)
    }

    fun moveUp() {
        if (currentWorkingDirectory.isRoot()) return
        currentWorkingDirectory = currentWorkingDirectory.parent!!
    }

    fun moveTo(name: String): FileSystem {
        if (name == MOVE_UP_NAME) {
            moveUp()
            return this
        }
        if (name == ROOT_DIRECTORY_NAME) {
            currentWorkingDirectory = ROOT_DIRECTORY
            return this
        }
        currentWorkingDirectory = currentWorkingDirectory.getSubDirectory(name)
        return this
    }

    fun moveToRoot() {
        currentWorkingDirectory = ROOT_DIRECTORY
    }

    fun hasFile(name: String) = currentWorkingDirectory.hasFile(name = name)
    fun createFile(name: String, size: Long) = currentWorkingDirectory.createFile(name = name, size = size)
    fun createDirectory(name: String) = currentWorkingDirectory.createDirectory(name = name)

    fun findTotalSize() = currentWorkingDirectory.findTotalSize()
    fun findTotalWithCapacity(maxSize: Long) = currentWorkingDirectory.findTotal(maxSize)
}

private class Directory(val parent: Directory?, val name: String) {
    private val subDirectories: MutableList<Directory> = mutableListOf()
    private val files: MutableList<File> = mutableListOf()

    fun isRoot() = parent == null

    fun createFile(name: String, size: Long) {
        files.add(File(name = name, size = size))
    }

    fun createDirectory(name: String) {
        val directory = Directory(parent = this, name = name)
        subDirectories.add(directory)
    }

    fun findTotalSize(): Long {
        if (subDirectories.isEmpty()) return files.sumOf { it.size }
        return subDirectories.sumOf { it.findTotalSize() } + files.sumOf { it.size }
    }

    fun findTotal(size: Long) = finn(size, this, mutableListOf()).sumOf { it.findTotalSize() }

    private fun finn(size: Long, directory: Directory, list: MutableList<Directory>): MutableList<Directory> {
        for (subDirectory in directory.subDirectories) {
            if (subDirectory.findTotalSize() <= size) {
                list.add(subDirectory)
            }
            finn(size, subDirectory, list)
        }
        return list
    }

    fun getSubDirectory(name: String) =
        subDirectories.find { it.name == name } ?: throw NotDirectoryException("Subdirectory $name not found")

    fun hasDirectory(name: String) = subDirectories.any { it.name == name }
    fun hasFile(name: String) = files.any { it.name == name }
}

data class File(val name: String, val size: Long)