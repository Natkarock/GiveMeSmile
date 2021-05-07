package com.natkarock.myapplication.frameworks.camera

import android.media.Image

interface SmileListener {
    fun smileDetected(image: Image, rotationDegree: Int)
}