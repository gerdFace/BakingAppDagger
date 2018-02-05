package com.example.android.bakingapplication.presentation

import com.example.android.bakingapplication.view.fragment.IngredientsFragmentView

interface IngredientsFragmentPresenter {

    fun loadIngredients()

    fun setView(view: IngredientsFragmentView)
}
