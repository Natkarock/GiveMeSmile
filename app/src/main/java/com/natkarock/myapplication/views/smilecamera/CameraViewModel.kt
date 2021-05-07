package com.natkarock.myapplication.views.smilecamera

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


enum class CameraScreenStates {
    SHOWFIRST,
    SHOWSECOND,
    SHOWTHIRD,
    COMPLETE
}

class CameraViewModel : ViewModel() {

    val screenState = MutableLiveData<CameraScreenStates?>()

    private fun setState(state: CameraScreenStates) {
        screenState.postValue(
            state
        )
    }

    fun showImagesAndComplete() {
        viewModelScope.launch {
            flowOf(
                CameraScreenStates.SHOWFIRST,
                CameraScreenStates.SHOWSECOND,
                CameraScreenStates.SHOWTHIRD,
                CameraScreenStates.COMPLETE
            )
                .onEach {
                    delay(500)
                }
                .collect {
                    setState(it)
                }
        }
    }


    fun getStateFlow(state: CameraScreenStates): Flow<CameraScreenStates> =
        flowOf(state)

}