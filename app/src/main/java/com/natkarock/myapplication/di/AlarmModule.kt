package com.natkarock.myapplication.di

import android.app.AlarmManager
import android.app.Service
import android.content.Context
import com.natkarock.myapplication.frameworks.notification.NotificationProvider
import com.natkarock.myapplication.frameworks.notification.NotificationManagerImpl
import com.natkarock.myapplication.frameworks.smileAlarm.SmileAlarmManager
import com.natkarock.myapplication.frameworks.smileAlarm.SmileManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlarmModule {


    @Provides
    @Singleton
    fun notificationManager(@ApplicationContext context: Context): NotificationProvider = NotificationManagerImpl(context)

    @Provides
    @Singleton
    fun smileManager(@ApplicationContext context: Context, alarmManager: AlarmManager): SmileManager = SmileAlarmManager(context, alarmManager)


    @Provides
    @Singleton
    fun alarmManager(@ApplicationContext context: Context): AlarmManager = context.getSystemService(Service.ALARM_SERVICE) as AlarmManager

}