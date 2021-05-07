package com.natkarock.myapplication.repository

interface CountRepository {
    fun updateCount(count: Int): Int
    fun getCount(): Int
}