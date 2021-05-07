package com.natkarock.myapplication.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.natkarock.myapplication.R
import com.natkarock.myapplication.views.counter.CounterFragment
import com.natkarock.myapplication.views.main.MainScreenState
import com.natkarock.myapplication.views.main.MainViewModel
import com.natkarock.myapplication.views.smilecamera.CameraFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    val viewmodel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initActionBar()
        observeScreenState()
    }

    private fun initActionBar(){
        supportActionBar?.title = ""
        supportActionBar?.elevation = 0f
    }

    private fun observeScreenState(){
        viewmodel.screenState.observe(this){ state ->
            when(state){
                MainScreenState.TO_COUNTER -> initFirstFragment(R.id.fragment_container, CounterFragment.create())
                MainScreenState.TO_CAMERA -> initFirstFragment(R.id.fragment_container, CameraFragment.create())
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}