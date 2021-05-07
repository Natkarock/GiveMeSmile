package com.natkarock.myapplication.frameworks.camera

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executor

interface CameraManager {


    fun init(context: Context, view: PreviewView, cameraExecutor: Executor, lifecycleOwner: LifecycleOwner)


    fun stop(context: Context)

    fun captureInBuffer()


}