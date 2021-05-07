package com.natkarock.myapplication.frameworks.camera

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.natkarock.myapplication.frameworks.faceDetection.FaceDetectionManager
import com.natkarock.myapplication.utils.toBitmap
import javax.inject.Inject


class CameraSmileAnalyzer @Inject constructor(
    private val faceDetectionManager: FaceDetectionManager,
    private val smileListener: SmileListener
) : ImageAnalysis.Analyzer {


    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        mediaImage?.let {

            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            faceDetectionManager.processImageForSmile(
                image,
                { smileListener.smileDetected(mediaImage, imageProxy.imageInfo.rotationDegrees) },
                { imageProxy.close() })
        }

    }

}