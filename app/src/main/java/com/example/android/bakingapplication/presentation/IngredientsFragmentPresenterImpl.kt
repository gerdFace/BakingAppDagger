package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.model.Ingredient
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.view.fragment.IngredientsFragmentView

class IngredientsFragmentPresenterImpl(private val recipeRepository: RecipeRepository) : IngredientsFragmentPresenter {

    private var view: IngredientsFragmentView? = null

    override fun loadIngredients() {
        val recipeId = view!!.getRecipeId()

        recipeRepository.getIngredients(recipeId, object:RecipeRepository.GetIngredientsCallback {
            override fun onIngredientsLoaded(ingredients: List<Ingredient>) {
                view!!.showIngredients(ingredients)
            }

            override fun onDataNotAvailable(failureMessage: String) {
                view!!.showErrorMessage(failureMessage)
            }
        })
    }

    override fun setView(view: IngredientsFragmentView) {
        this.view = view
        loadIngredients()
    }
}
