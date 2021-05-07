package com.natkarock.myapplication.views.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natkarock.myapplication.repository.CountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val countRepository: CountRepository): ViewModel() {

    val screenState: MutableLiveData<MainScreenState> = MutableLiveData()

    init {
        initScreen()
    }

    private fun initScreen(){
        val state = if(countRepository.getCount() > 0) MainScreenState.TO_CAMERA else MainScreenState.TO_COUNTER
        screenState.postValue(state)
    }

}

enum class MainScreenState{
    TO_COUNTER,
    TO_CAMERA
}