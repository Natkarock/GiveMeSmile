package com.natkarock.myapplication.utils

import java.util.*
import java.util.concurrent.TimeUnit

object TimeUtils {
    private val MILLIS_IN_DAY = TimeUnit.DAYS.toMillis(1)
    private val SECONDS_IN_HOUR = TimeUnit.HOURS.toSeconds(1)
    private const val START_HOUR = 9
    private const val END_HOUR = 20
    private val ENABLE_DAY_PERIOD = SECONDS_IN_HOUR * (END_HOUR - START_HOUR)


    fun dayIntervalByCount(count: Int): Long = (ENABLE_DAY_PERIOD / count).toLong()


    fun getStartIntervalTime(count: Int): Long {
        if (isLateForAlarm()) return getNextDayStartMillis()
        return getStartCalendar(count).timeInMillis
    }


    private fun getStartCalendar(count: Int): Calendar {
        val startSecond = START_HOUR * SECONDS_IN_HOUR
        val interval = dayIntervalByCount(count)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MILLISECOND, 0)
        val seconds =
            calendar.get(Calendar.HOUR_OF_DAY) * SECONDS_IN_HOUR + calendar.get(Calendar.MINUTE) * 60 + calendar.get(
                Calendar.SECOND
            )
        var currentIntervalSecond = count
        for (index in 1..count) {
            val intervalSeconds = interval * index + startSecond
            if (intervalSeconds > seconds) {
                currentIntervalSecond = intervalSeconds.toInt()
                break
            }
        }
        val hour: Int = currentIntervalSecond / (SECONDS_IN_HOUR).toInt()
        val minute: Int = (currentIntervalSecond - (hour * SECONDS_IN_HOUR.toInt())) / 60
        val second = (currentIntervalSecond - (hour * SECONDS_IN_HOUR) - (minute * 60)).toInt()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        return calendar
    }

    private fun isLateForAlarm(): Boolean {
        val calendar = Calendar.getInstance()
        if (calendar.get(Calendar.HOUR_OF_DAY) >= END_HOUR) {
            return true
        }
        return false
    }

    private fun getNextDayStartMillis(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, START_HOUR)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        return calendar.timeInMillis + MILLIS_IN_DAY
    }


}