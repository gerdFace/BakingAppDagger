package com.example.android.bakingapplication.view.fragment

import com.example.android.bakingapplication.model.Ingredient

interface IngredientsFragmentView {

    fun getRecipeId(): Int

    fun showIngredients(ingredientList: List<Ingredient>)

    fun showErrorMessage(failureMessage: String)
}
