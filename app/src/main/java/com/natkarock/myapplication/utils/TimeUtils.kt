package com.natkarock.myapplication.utils

import java.util.*

object TimeUtils {
    private const val MILLIS_IN_DAY = 60*60*24*1000
    private const val START_HOUR = 9
    private const val END_HOUR = 20
    private const val ENABLE_DAY_PERIOD = 60 * 60 * (END_HOUR - START_HOUR)


    fun dayIntervalByCount(count: Int): Long = (ENABLE_DAY_PERIOD / count).toLong()


    fun getStartIntervalTime(count: Int): Long {
        if(isLateForAlarm()) return getNextDayStartMillis()
        return getStartCalendar(count).timeInMillis
    }


    private fun getStartCalendar(count: Int): Calendar {
        val startSecond = START_HOUR * 60 * 60
        val interval = dayIntervalByCount(count)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MILLISECOND, 0)
        val seconds =
            calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(
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
        val hour: Int = currentIntervalSecond / (60 * 60)
        val minute: Int = (currentIntervalSecond - (hour * 60 * 60)) / 60
        val second = (currentIntervalSecond - (hour * 60 * 60) - (minute * 60))
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, second)
        return calendar
    }

    private fun isLateForAlarm():Boolean {
        val calendar = Calendar.getInstance()
        if(calendar.get(Calendar.HOUR_OF_DAY)>= END_HOUR){
            return  true
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