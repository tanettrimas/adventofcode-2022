package adventofcode2022.app.day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SubRoutineTest {

    @Test
    fun startOfPacketMarkerWithDefault() {
        assertEquals(5, SubRoutine("bvwbjplbgvbhsrlpgdmjqwftvncz").startOfPacketMarker())
        assertEquals(6, SubRoutine("nppdvjthqldpwncqszvftbrmjlhg").startOfPacketMarker())
        assertEquals(10, SubRoutine("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").startOfPacketMarker())
        assertEquals(11, SubRoutine("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").startOfPacketMarker())
    }

    @Test
    fun withSpecificSize() {
        val distinctSizes = 14
        assertEquals(19, SubRoutine("mjqjpqmgbljsphdztnvjfqwrcgsmlb", windowSize = distinctSizes).startOfPacketMarker())
        assertEquals(23, SubRoutine("bvwbjplbgvbhsrlpgdmjqwftvncz", windowSize = distinctSizes).startOfPacketMarker())
        assertEquals(23, SubRoutine("nppdvjthqldpwncqszvftbrmjlhg", windowSize = distinctSizes).startOfPacketMarker())
        assertEquals(29, SubRoutine("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", windowSize = distinctSizes).startOfPacketMarker())
        assertEquals(26, SubRoutine("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", windowSize = distinctSizes).startOfPacketMarker())
    }
}