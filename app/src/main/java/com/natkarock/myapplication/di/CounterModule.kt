package com.natkarock.myapplication.di

import com.natkarock.myapplication.frameworks.DataManager
import com.natkarock.myapplication.repository.CountRepository
import com.natkarock.myapplication.repository.CountRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class CounterModule {

    @Provides
    @ViewModelScoped
    fun counterRepository(dataManager: DataManager): CountRepository = CountRepositoryImpl(dataManager)
}