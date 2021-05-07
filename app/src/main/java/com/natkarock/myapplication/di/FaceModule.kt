package com.natkarock.myapplication.di


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.mlkit.vision.face.FaceDetector
import com.natkarock.myapplication.frameworks.FaceDetectionManagerImpl
import com.natkarock.myapplication.frameworks.camera.CameraFaceManager
import com.natkarock.myapplication.frameworks.camera.CameraManager
import com.natkarock.myapplication.frameworks.camera.CameraSmileAnalyzer
import com.natkarock.myapplication.frameworks.faceDetection.FaceDetectionManager
import com.natkarock.myapplication.frameworks.faceDetection.FaceDetectionManagerFactory
import com.natkarock.myapplication.views.smilecamera.CameraFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class FaceModule {

    @Provides
    @FragmentScoped
    fun faceDetector() = FaceDetectionManagerFactory.create(FaceDetectionManagerImpl.getOptions())

    @Provides
    @FragmentScoped
    fun faceDetectionManager(faceDetector: FaceDetector):FaceDetectionManager = FaceDetectionManagerImpl(faceDetector)


    @Provides
    @FragmentScoped
    fun smileAnalyzer(faceDetectionManager: FaceDetectionManager, fragment: Fragment) = CameraSmileAnalyzer(faceDetectionManager, fragment as CameraFragment)

    @Provides
    @FragmentScoped
    fun cameraManager(smileAnalyzer: CameraSmileAnalyzer): CameraManager = CameraFaceManager(smileAnalyzer)

}