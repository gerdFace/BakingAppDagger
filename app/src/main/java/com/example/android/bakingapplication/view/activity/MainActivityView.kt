package com.example.android.bakingapplication.view.activity

import com.example.android.bakingapplication.model.RecipeData

interface MainActivityView {

    fun showRecipes(recipeList: List<RecipeData>)

    fun showErrorMessage(errorMessage: String)

}
