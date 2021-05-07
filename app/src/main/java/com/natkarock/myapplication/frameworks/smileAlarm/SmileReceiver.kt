package com.natkarock.myapplication.frameworks.smileAlarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.natkarock.myapplication.frameworks.notification.NotificationProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SmileReceiver: BroadcastReceiver() {

    @Inject lateinit var notificationProvider: NotificationProvider


    override fun onReceive(p0: Context?, p1: Intent?) {
        notificationProvider.show()
    }
}