package com.natkarock.myapplication.frameworks.smileAlarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.natkarock.myapplication.frameworks.DataManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class BootAlarmReceiver : BroadcastReceiver() {
    @Inject
    lateinit var smileManager: SmileManager
    @Inject
    lateinit var dataManager: DataManager

    override fun onReceive(p0: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            val smileCount = dataManager.smileCount
            smileManager.setAlarmPerDay(smileCount)
        }
    }

}