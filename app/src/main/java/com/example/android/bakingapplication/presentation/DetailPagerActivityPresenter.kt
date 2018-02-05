package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.view.activity.DetailPagerActivityView

interface DetailPagerActivityPresenter {

    fun loadSteps()

    fun setView(view: DetailPagerActivityView)
}
