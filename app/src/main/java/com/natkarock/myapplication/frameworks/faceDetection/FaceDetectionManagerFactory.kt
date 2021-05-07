package com.natkarock.myapplication.frameworks.faceDetection


import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions

object FaceDetectionManagerFactory {
    fun create(options: FaceDetectorOptions): FaceDetector = FaceDetection.getClient(options)
}