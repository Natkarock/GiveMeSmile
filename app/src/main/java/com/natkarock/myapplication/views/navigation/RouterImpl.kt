package com.natkarock.myapplication.views.navigation

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.natkarock.myapplication.R
import com.natkarock.myapplication.views.counter.CounterFragment
import com.natkarock.myapplication.views.smilecamera.CameraFragment
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject


@FragmentScoped
class RouterImpl @Inject constructor(private val baseFragment: Fragment) : Router {

    val backCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            pop()
        }
    }

    override fun addBackCallback() {
        baseFragment.requireActivity().onBackPressedDispatcher.addCallback(baseFragment, backCallback)
    }

    override fun toCamera() {
        toFragmentWithoutHistory(CameraFragment.create())
    }

    override fun toCounter() {
        toFragmentWithoutHistory(CounterFragment.create())
    }


    fun toFragmentWithoutHistory(fragment: Fragment) {
        baseFragment.parentFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun pop() {
        if (baseFragment.parentFragmentManager.backStackEntryCount > 1) {
            baseFragment.parentFragmentManager.popBackStack()
        } else {
            baseFragment.requireActivity().finish()
        }
    }

}