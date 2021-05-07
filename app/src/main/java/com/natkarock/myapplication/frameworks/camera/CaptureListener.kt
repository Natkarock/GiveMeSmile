package com.natkarock.myapplication.frameworks.camera

import android.media.Image

interface CaptureListener {
    fun captured(image: Image)
}