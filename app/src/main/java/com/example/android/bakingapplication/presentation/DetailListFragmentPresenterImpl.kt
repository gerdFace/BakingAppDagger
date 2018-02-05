package com.example.android.bakingapplication.presentation

import android.util.Log

import com.example.android.bakingapplication.model.RecipeData
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.view.fragment.DetailListFragmentView

class DetailListFragmentPresenterImpl(private val recipeRepository: RecipeRepository) : DetailListFragmentPresenter {

    private var view: DetailListFragmentView? = null

    override fun loadSteps() {
        val recipeId = view!!.recipeId

        recipeRepository.getRecipe(recipeId, object:RecipeRepository.GetRecipeCallback {
            override fun onRecipeLoaded(recipe: RecipeData) {
                view!!.showSteps(recipe.steps)
                view!!.updateWidgets()
            }

            override fun onDataNotAvailable(failureMessage: String) {
                Log.d(TAG, "Could not load steps " + failureMessage)
            }
        })
    }

    override fun setView(view: DetailListFragmentView) {
        this.view = view
        loadSteps()
    }

    companion object {

        private val TAG = DetailListFragmentPresenterImpl::class.java.simpleName
    }
}
