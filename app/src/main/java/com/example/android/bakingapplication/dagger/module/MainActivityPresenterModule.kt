package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.presentation.MainActivityPresenter
import com.example.android.bakingapplication.presentation.MainActivityPresenterImpl
import com.example.android.bakingapplication.repository.RecipeRepository

import dagger.Module
import dagger.Provides

@Module
class MainActivityPresenterModule {

    @Provides
    fun provideMainActivityPresenter(recipeRepository: RecipeRepository): MainActivityPresenter {
        return MainActivityPresenterImpl(recipeRepository)
    }
}
