package com.natkarock.myapplication.views.smilecamera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.natkarock.myapplication.R
import com.natkarock.myapplication.databinding.FragmentCameraBinding
import com.natkarock.myapplication.frameworks.camera.CameraManager
import com.natkarock.myapplication.frameworks.camera.SmileListener
import com.natkarock.myapplication.utils.toBitmap
import com.natkarock.myapplication.views.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@AndroidEntryPoint
class CameraFragment : BaseFragment(), SmileListener {

    @Inject
    lateinit var cameraManager: CameraManager

    lateinit var binding: FragmentCameraBinding
    private lateinit var cameraExecutor: ExecutorService
    private val cameraViewModel: CameraViewModel by viewModels()


    companion object {
        private const val ANIMATION_REPEAT = 1
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        fun create(): CameraFragment {
            return CameraFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        cameraExecutor = Executors.newSingleThreadExecutor()
        initView()
        initCamera()
        initLottieView()
        observeScreenState()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_camera, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_settings -> {
                router.toCounter()
                return true
            }
        }
        return false
    }


    private fun initView() {
        setHasOptionsMenu(true)
        binding.apply {
            img1.isVisible = false
            img2.isVisible = false
            img3.isVisible = false
        }
    }

    override fun smileDetected(image: Image, rotationDegree: Int) {
        cameraManager.stop(requireContext())
        val bitmap = image.toBitmap(rotationDegree)
        setBitmapToImgs(bitmap)
        binding.lottieView.apply {
            isVisible = true
            repeatCount = ANIMATION_REPEAT
            playAnimation()
        }
    }

    private fun animationEnd() {
        binding.apply {
            lottieView.isVisible = false
            previewView.isVisible = false
        }
        cameraViewModel.showImagesAndComplete()
    }

    private fun setBitmapToImgs(bitmap: Bitmap) {
        binding.apply {
            img1.setImageBitmap(bitmap)
            img2.setImageBitmap(bitmap)
            img3.setImageBitmap(bitmap)
        }
    }


    private fun initCamera() {
        // Request camera permissions
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            requestPermissions(
                REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }
    }

    private fun initLottieView() {
        binding.lottieView.addAnimatorListener(CountLottieAnimationListener(ANIMATION_REPEAT) {
            animationEnd()
        })
    }

    private fun startCamera() {
        cameraManager.init(requireActivity(), binding.previewView, cameraExecutor, this)
    }


    private fun observeScreenState() {
        cameraViewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CameraScreenStates.SHOWFIRST -> binding.img1.isVisible = true
                CameraScreenStates.SHOWSECOND -> binding.img2.isVisible = true
                CameraScreenStates.SHOWTHIRD -> binding.img3.isVisible = true
                CameraScreenStates.COMPLETE -> requireActivity().finish()
            }
        }
    }


    //request permissions
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().finish()
            }
        }
    }


}