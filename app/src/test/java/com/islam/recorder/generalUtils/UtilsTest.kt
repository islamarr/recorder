package com.islam.recorder.generalUtils

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun `millisToMinutesAndSeconds return equivalent seconds and minutes`() {
        val milliseconds1 = 100L
        val actual1 = "00 : 00"
        val expected1 = Utils.millisToMinutesAndSeconds(milliseconds1)
        assertEquals(expected1, actual1)

        val milliseconds2 = 20000L
        val actual2 = "00 : 20"
        val expected2 = Utils.millisToMinutesAndSeconds(milliseconds2)
        assertEquals(expected2, actual2)

        val milliseconds3 = 71000L
        val actual3 = "01 : 11"
        val expected3 = Utils.millisToMinutesAndSeconds(milliseconds3)
        assertEquals(expected3, actual3)

        val milliseconds4 = 60000L * 61
        val actual4 = "61 : 00"
        val expected4 = Utils.millisToMinutesAndSeconds(milliseconds4)
        assertEquals(expected4, actual4)


    }

}