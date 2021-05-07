package com.natkarock.myapplication.di

import androidx.fragment.app.Fragment
import com.natkarock.myapplication.views.navigation.Router
import com.natkarock.myapplication.views.navigation.RouterImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped


@Module
@InstallIn(FragmentComponent::class)
class RouterModule {

    @Provides
    @FragmentScoped
    fun router(fragment: Fragment): Router = RouterImpl(fragment)
}