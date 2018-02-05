package com.example.android.bakingapplication.view.activity

import com.example.android.bakingapplication.model.Step

interface DetailPagerActivityView {

    var recipeId: Int

    fun setSteps(steps: List<Step>)

    fun showErrorMessage(failureMessage: String)
}
