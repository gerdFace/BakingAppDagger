package com.example.android.bakingapplication.presentation

import android.content.Context
import android.net.ConnectivityManager
import com.example.android.bakingapplication.model.RecipeData
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.view.activity.MainActivityView

class MainActivityPresenterImpl(private val recipeRepository: RecipeRepository) : MainActivityPresenter {

    private var view: MainActivityView? = null

    override fun loadRecipes() {
        recipeRepository.getRecipes(object:RecipeRepository.LoadRecipesCallback {
            override fun onRecipesLoaded(recipes: List<RecipeData>) {
                view!!.showRecipes(recipes)
            }

            override fun onDataNotAvailable(failureMessage: String) {
                view!!.showErrorMessage(failureMessage)
            }
        })
    }

    override fun isDeviceOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    override fun setView(view: MainActivityView) {
        this.view = view
        loadRecipes()
    }
}
