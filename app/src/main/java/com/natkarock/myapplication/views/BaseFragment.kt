package com.natkarock.myapplication.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.natkarock.myapplication.views.navigation.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
open class BaseFragment : Fragment() {


    @Inject
    lateinit var router:Router


    override fun onCreate(savedInstanceState: Bundle?) {
        router.addBackCallback()
        super.onCreate(savedInstanceState)
    }



}