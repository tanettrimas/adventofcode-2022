package adventofcode2022.app.day7

import java.nio.file.NotDirectoryException

private const val ROOT_DIRECTORY_NAME = "/"
private const val MOVE_UP_NAME = ".."

class FileSystem {
    companion object {
        private const val TOTAL_DISK_SPACE = 70000000
        private const val MINIMUM_UPDATE_SPACE = 30000000
    }

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

    private fun usedSpace(): Long {
        return ROOT_DIRECTORY.findTotalSize()
    }

    fun hasFile(name: String) = currentWorkingDirectory.hasFile(name = name)

    fun createFile(name: String, size: Long) = currentWorkingDirectory.createFile(name = name, size = size)
    fun createDirectory(name: String) = currentWorkingDirectory.createDirectory(name = name)
    fun findTotalSize() = currentWorkingDirectory.findTotalSize()

    fun findTotalWithCapacity(maxSize: Long) = currentWorkingDirectory.findDirectories {
        it.findTotalSize() <= maxSize
    }.sumOf { it.findTotalSize() }

    fun findTotalSizeForDiskUpdate(): Long {
        val spaceRequiredForUpdate = MINIMUM_UPDATE_SPACE - (TOTAL_DISK_SPACE - usedSpace())

        return currentWorkingDirectory.findDirectories {
            it.findTotalSize() >= spaceRequiredForUpdate
        }.minBy { it.findTotalSize() }.findTotalSize()
    }
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

    fun findDirectories(predicate: (directory: Directory) -> Boolean) =
        finn(this, mutableListOf(), predicate).toMutableList()
            .apply {
                if (predicate(this@Directory)) {
                    add(this@Directory)
                }
            }.toList()

    private fun finn(
        directory: Directory,
        list: MutableList<Directory>,
        predicate: (directory: Directory) -> Boolean
    ): List<Directory> {
        for (subDirectory in directory.subDirectories) {
            if (predicate(subDirectory)) {
                list.add(subDirectory)
            }
            finn(subDirectory, list, predicate)
        }
        return list.toList()
    }

    fun getSubDirectory(name: String) =
        subDirectories.find { it.name == name } ?: throw NotDirectoryException("Subdirectory $name not found")

    fun hasDirectory(name: String) = subDirectories.any { it.name == name }
    fun hasFile(name: String) = files.any { it.name == name }
}

data class File(val name: String, val size: Long)