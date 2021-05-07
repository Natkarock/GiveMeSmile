package com.natkarock.myapplication.utils

import junit.framework.TestCase

class TimeUtilsTest : TestCase() {


    fun testDayIntervalByCount() {
        val count = 6
        val second = TimeUtils.dayIntervalByCount(count)
        assertEquals(second, 14400)
    }

    fun testGetStartIntervalTime() {}
}