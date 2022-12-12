package adventofcode2022.app.day7

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class FileSystemTest {

    private lateinit var fileSystem: FileSystem

    @BeforeEach
    fun setUp() {
        fileSystem = FileSystem()
    }

    @Test
    fun `can create directory`() {
        assertTrue(fileSystem.hasDirectory(name = "/"))
        fileSystem.createDirectory("a")
        assertTrue(fileSystem.hasDirectory("a"))
        assertFalse(fileSystem.hasDirectory("b"))
    }

    @Test
    fun `can create file`() {
        fileSystem.createFile("a", 100)
        assertTrue(fileSystem.hasFile("a"))
        assertFalse(fileSystem.hasFile("b"))
    }

    @Test
    /**
     *
     * - / (dir)
     *   - a (dir)
     *     - e (dir)
     *       - i (file, size=584)
     *     - f (file, size=29116)
     *     - g (file, size=2557)
     *     - h.lst (file, size=62596)
     *   - b.txt (file, size=14848514)
     *   - c.dat (file, size=8504156)
     *   - d (dir)
     *     - j (file, size=4060174)
     *     - d.log (file, size=8033020)
     *     - d.ext (file, size=5626152)
     *     - k (file, size=7214296)
     *
     * */
    fun `spec test`() {
        setupFilesystem()
        fileSystem.moveTo("a")
        assertEquals(94853, fileSystem.findTotalSize())
        fileSystem.moveUp()
        fileSystem.moveTo("d")
        assertEquals(24933642, fileSystem.findTotalSize())
        fileSystem.moveUp()
        fileSystem
            .moveTo("a")
        fileSystem.moveTo("e")
        assertEquals(584, fileSystem.findTotalSize())
        fileSystem.moveToRoot()
        assertEquals(48381165, fileSystem.findTotalSize())
    }

    private fun setupFilesystem() {
        fileSystem.createDirectory("a")
        fileSystem.createDirectory("d")
        fileSystem.createFile("b.txt", 14848514)
        fileSystem.createFile("c.dat", 8504156)
        fileSystem.moveTo("a")
        fileSystem.createFile("f", 29116)
        fileSystem.createFile("g", 2557)
        fileSystem.createFile("h.lst", 62596)
        fileSystem.createDirectory("e")
        fileSystem.moveTo("e")
        fileSystem.createFile("i", 584)
        fileSystem.moveToRoot()
        fileSystem.moveTo("d")
        fileSystem.createFile("j", 4060174)
        fileSystem.createFile("d", 8033020)
        fileSystem.createFile("d", 5626152)
        fileSystem.createFile("k", 7214296)
        fileSystem.moveToRoot()
    }

    @Test
    fun task1Test() {
        setupFilesystem()
        assertEquals(95437, fileSystem.findTotalWithCapacity(100000))
    }

    @Test
    fun task2Test() {
        setupFilesystem()
        assertEquals(24933642, fileSystem.findTotalSizeForDiskUpdate())
    }
}