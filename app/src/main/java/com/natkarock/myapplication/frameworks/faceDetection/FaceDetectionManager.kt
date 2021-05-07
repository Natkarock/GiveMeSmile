package com.natkarock.myapplication.frameworks.faceDetection

import android.graphics.Bitmap
import com.google.mlkit.vision.common.InputImage

interface FaceDetectionManager {

    fun processImageForSmile(image: InputImage, success: ()-> Unit, release: () -> Unit)
}