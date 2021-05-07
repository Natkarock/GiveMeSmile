package com.natkarock.myapplication.frameworks.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager.IMPORTANCE_DEFAULT
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.natkarock.myapplication.BuildConfig
import com.natkarock.myapplication.R
import com.natkarock.myapplication.views.MainActivity
import javax.inject.Inject

class NotificationManagerImpl @Inject constructor(private val context: Context) :
    NotificationProvider {


    init {
        createNotificationChannel()
    }

    override fun show() {
         val notification = createNotification()
         with(NotificationManagerCompat.from(context)){
                notify(123, notification)
            }
    }


    private fun createNotification(): Notification {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val title = context.resources.getString(R.string.notification_title)
        val text = context.resources.getString(R.string.notification_text)
        return NotificationCompat.Builder(context, BuildConfig.APPLICATION_ID).setSmallIcon(R.drawable.ic_notification).setAutoCancel(true)
            .setChannelId(BuildConfig.APPLICATION_ID).setContentTitle(title).setContentText(text).setPriority(NotificationCompat.PRIORITY_MAX).setContentIntent(pendingIntent).build()
    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "GiveMeSmile"
            val descriptionText = "GiveMeSmileDescription"
            val importance = IMPORTANCE_DEFAULT
            val channel = NotificationChannel(BuildConfig.APPLICATION_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }


}