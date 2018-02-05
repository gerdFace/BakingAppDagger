package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.presentation.IngredientsFragmentPresenter
import com.example.android.bakingapplication.presentation.IngredientsFragmentPresenterImpl
import com.example.android.bakingapplication.repository.RecipeRepository

import dagger.Module
import dagger.Provides

@Module(includes = [RecipeRepositoryModule::class])
class IngredientsFragmentPresenterModule {

    @Provides
    fun provideIngredientsFragmentPresenter(recipeRepository: RecipeRepository): IngredientsFragmentPresenter {
        return IngredientsFragmentPresenterImpl(recipeRepository)
    }
}
