package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.view.fragment.DetailListFragmentView

interface DetailListFragmentPresenter {

    fun loadSteps()

    fun setView(view: DetailListFragmentView)
}
