package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.model.Step
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.view.activity.DetailPagerActivityView

class DetailPagerActivityPresenterImpl(private val recipeRepository: RecipeRepository) : DetailPagerActivityPresenter {

    private var view: DetailPagerActivityView? = null

    override fun loadSteps() {
        val recipeId = view!!.recipeId

        recipeRepository.getSteps(recipeId, object : RecipeRepository.GetStepsCallback {
            override fun onStepsLoaded(steps: List<Step>) {
                view!!.setSteps(steps)
            }

            override fun onDataNotAvailable(failureMessage: String) {
                view!!.showErrorMessage(failureMessage)
            }
        })
    }

    override fun setView(view: DetailPagerActivityView) {
        this.view = view
        loadSteps()
    }
}
