package com.natkarock.myapplication.frameworks.smileAlarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.natkarock.myapplication.utils.TimeUtils
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SmileAlarmManager @Inject constructor(
    private val context: Context,
    private val alarmManager: AlarmManager
) : SmileManager {

    override fun setAlarmPerDay(count: Int) {
        cancelAlarms()
        setRepeatingAlarm(count)
    }

    override fun cancelAlarms() {
        val pending = getPending()
        alarmManager.cancel(pending)
    }


    private fun setRepeatingAlarm(count: Int) {
        val interval = TimeUtils.dayIntervalByCount(count)
        val startTimeInMillis = TimeUtils.getStartIntervalTime(count)
        val updateMillis = if(System.currentTimeMillis() > startTimeInMillis) System.currentTimeMillis() + 5000 else startTimeInMillis
        //alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3000,  getPending())
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            updateMillis ,
            interval * 1000,
            getPending()
        )
    }


    private fun getPending(): PendingIntent {
        val intent = Intent(context, SmileReceiver::class.java)
        intent.action = "com.natkarock.myapplication.ActionSetter"
        return PendingIntent.getBroadcast(context, ALARM_PENDING_REQUEST_ID, intent, 0)

    }

    companion object {
        private const val ALARM_PENDING_REQUEST_ID = 123
    }
}