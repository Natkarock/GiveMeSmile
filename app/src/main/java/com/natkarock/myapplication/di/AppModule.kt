package com.natkarock.myapplication.di

import android.content.Context
import android.content.SharedPreferences
import com.natkarock.myapplication.frameworks.DataManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun sharedPreferences(@ApplicationContext context: Context) = context.getSharedPreferences(context.packageName, 0)

    @Provides
    @Singleton
    fun dataManager(sharedPreferences: SharedPreferences) = DataManager(sharedPreferences)


}