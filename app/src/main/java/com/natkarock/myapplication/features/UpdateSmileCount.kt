package com.natkarock.myapplication.features

import com.natkarock.myapplication.frameworks.smileAlarm.SmileManager
import com.natkarock.myapplication.repository.CountRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class UpdateSmileCount @Inject constructor (private  val countRepository: CountRepository, private val smileManager: SmileManager) {

        private val countFlow = MutableSharedFlow<Int>()


        suspend fun setup(successCallback: (count: Int)->Unit){
            countFlow.
            onStart { getCount() }.
            collect { count ->
                successCallback.invoke(count)
            }
        }

        suspend fun update(count: Int){
            val updatedCount  = countRepository.updateCount(count)
            smileManager.setAlarmPerDay(count)
            countFlow.emit(updatedCount)
        }

       suspend fun getCount(){
            val count = countRepository.getCount()
            countFlow.emit(count)
        }
}