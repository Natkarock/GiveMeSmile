package com.natkarock.myapplication.views

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity: AppCompatActivity() {

    protected fun initFirstFragment(containerId: Int, fragment: Fragment){
        supportFragmentManager.beginTransaction().add(containerId, fragment).commit()
    }


//    fun toFragmentWithoutHistory(fragment: Fragment) {
//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
//    }
//
//    fun pop() {
//        if (supportFragmentManager.backStackEntryCount > 1) {
//            supportFragmentManager.popBackStack()
//        } else {
//            finish()
//        }
//    }
}