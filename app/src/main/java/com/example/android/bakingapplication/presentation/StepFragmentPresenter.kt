package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.view.fragment.StepFragmentView

interface StepFragmentPresenter {

    fun initializeVideoPlayer()

    fun updateUI()

    fun pauseVideoPlayer()

    fun releaseVideoPlayer()

    fun loadStep()

    fun setView(view: StepFragmentView)

    fun getPlayerPosition(): Long
}
