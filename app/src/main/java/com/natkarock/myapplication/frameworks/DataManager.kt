package com.natkarock.myapplication.frameworks

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class DataManager @Inject constructor(private val preferences: SharedPreferences) {
    companion object {
        private const val SMILE_COUNT = "SMILE_COUNT"
        private const val DEFAULT_SMILE_COUNT = 0
    }

    var smileCount: Int
        get() = preferences.getInt(SMILE_COUNT, DEFAULT_SMILE_COUNT)
        set(value) {
            preferences.edit { putInt(SMILE_COUNT, value) }
        }
}