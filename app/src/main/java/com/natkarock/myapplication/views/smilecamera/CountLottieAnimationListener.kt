package com.natkarock.myapplication.views.smilecamera

import android.animation.Animator

class CountLottieAnimationListener(private val repeatCount: Int, private val onFinish: () -> Unit) :
    Animator.AnimatorListener {
    private var startCount = 0
    override fun onAnimationStart(p0: Animator?) {

    }

    override fun onAnimationEnd(p0: Animator?) {
        onFinish.invoke()
    }

    override fun onAnimationCancel(p0: Animator?) {

    }

    override fun onAnimationRepeat(p0: Animator?) {
//        startCount++
//        if (startCount == repeatCount) {
//
//        }
    }

}