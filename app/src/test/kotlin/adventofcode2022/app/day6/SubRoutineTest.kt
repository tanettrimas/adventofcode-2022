package adventofcode2022.app.day6

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SubRoutineTest {

    @Test
    fun startOfPacketMarker() {
        assertEquals(5, SubRoutine("bvwbjplbgvbhsrlpgdmjqwftvncz").startOfPacketMarker())
        assertEquals(6, SubRoutine("nppdvjthqldpwncqszvftbrmjlhg").startOfPacketMarker())
        assertEquals(10, SubRoutine("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg").startOfPacketMarker())
        assertEquals(11, SubRoutine("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw").startOfPacketMarker())
    }
}