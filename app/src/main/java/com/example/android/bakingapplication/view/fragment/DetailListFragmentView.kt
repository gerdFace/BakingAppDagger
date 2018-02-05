package com.example.android.bakingapplication.view.fragment

import com.example.android.bakingapplication.model.Step

interface DetailListFragmentView {

    val recipeId: Int

    fun showSteps(steps: List<Step>)

    fun updateWidgets()
}
