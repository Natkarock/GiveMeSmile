package com.natkarock.myapplication.frameworks

import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.Face
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import com.natkarock.myapplication.frameworks.faceDetection.FaceDetectionManager
import javax.inject.Inject


class FaceDetectionManagerImpl @Inject constructor(private val faceDetector: FaceDetector) :
    FaceDetectionManager {


    companion object {
        fun getOptions() =
            FaceDetectorOptions.Builder().setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
                .build()

    }

    override fun processImageForSmile(image: InputImage, success: () -> Unit, release: () -> Unit) {
        faceDetector.process(image).addOnSuccessListener { faces ->
            if(faces.isEmpty()){
                release.invoke()
                return@addOnSuccessListener
            }
            for (face in faces) {
                if (isFaceHaveSmile(face)) {
                    success.invoke()
                }else {
                    release.invoke()
                }
            }
        }.addOnFailureListener {
            release.invoke()
        }.addOnCanceledListener {
            release.invoke()
        }.addOnCompleteListener {
            release.invoke()
        }
    }

    private fun isFaceHaveSmile(face: Face): Boolean {
        face.smilingProbability?.let {
            return it > 0.9f
        }
        return false
    }

}