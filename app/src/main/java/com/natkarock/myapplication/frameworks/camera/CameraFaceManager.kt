package com.natkarock.myapplication.frameworks.camera

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.Surface
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject



class CameraFaceManager @Inject constructor( private val cameraSmileAnalyzer: CameraSmileAnalyzer): CameraManager {
    private lateinit var imageCapture: ImageCapture
    private lateinit var preview: Preview
    private lateinit var imageAnalyzer: ImageAnalysis


    override fun init(context: Context, view: PreviewView, cameraExecutor: Executor, lifecycleOwner: LifecycleOwner) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener( {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(view.surfaceProvider)
                }

            imageAnalyzer = ImageAnalysis.Builder()
                .build()
                .apply {
                    setAnalyzer(cameraExecutor, cameraSmileAnalyzer)
                }

            imageCapture = ImageCapture.Builder().build()


            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()
                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    lifecycleOwner, cameraSelector, preview, imageAnalyzer)

            } catch(exc: Exception) {
                Log.e(CameraFaceManager::class.java.canonicalName, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(context))
    }

    override fun stop(context: Context) {
        val cameraProviderFuture  = ProcessCameraProvider.getInstance(context)
        cameraProviderFuture.addListener({
            val provider = cameraProviderFuture.get()
            provider.unbindAll()
        }, ContextCompat.getMainExecutor(context))
    }

    override fun captureInBuffer() {
        imageCapture.takePicture(Executors.newSingleThreadExecutor(), object :
            ImageCapture.OnImageCapturedCallback() {

            @SuppressLint("UnsafeExperimentalUsageError")
            override fun onCaptureSuccess(imageProxy: ImageProxy) {
                super.onCaptureSuccess(imageProxy)
                val image = imageProxy.image
                image?.let {
                    //captureListener.captured(it)
                }
            }
        }
        )
    }


}