package com.natkarock.myapplication.repository

import com.natkarock.myapplication.frameworks.DataManager
import javax.inject.Inject

class CountRepositoryImpl @Inject constructor(private val manager: DataManager): CountRepository {
    override fun updateCount(count: Int): Int {
        val count = if(count > MIN_COUNT) count else MIN_COUNT
        manager.smileCount = count
        return  count
    }

    override fun getCount(): Int  = manager.smileCount


    companion object {
        private  const val MIN_COUNT = 1
    }
}