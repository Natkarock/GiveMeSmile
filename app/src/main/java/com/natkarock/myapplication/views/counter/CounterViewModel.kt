package com.natkarock.myapplication.views.counter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.natkarock.myapplication.features.UpdateSmileCount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(private  val updateSmileCount: UpdateSmileCount) : ViewModel() {

    val count = MutableLiveData(0)
    val screenState: MutableLiveData<CounterScreenState> = MutableLiveData()

    init {
        viewModelScope.launch {
            updateSmileCount.setup {
                count.postValue(it)
            }
        }
        getCount()
    }
    fun getCount(){
        viewModelScope.launch {
            updateSmileCount.getCount()
        }
    }

    fun increment(){
        viewModelScope.launch {
            count.value?.let {
                count.postValue(it + 1)
            }
        }
    }

    fun decrement(){
        viewModelScope.launch {
            count.value?.let {
                if(count.value != 0) {
                    count.postValue(it - 1)
                }
            }
        }
    }


    fun update(){
        viewModelScope.launch {
            count.value?.let {
                updateSmileCount.update(it)
            }

        }
        screenState.postValue(CounterScreenState.COMPLETE)
    }
}

enum class CounterScreenState{
    COMPLETE
}
