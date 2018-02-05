package com.example.android.bakingapplication.dagger.module

import android.content.Context

import com.example.android.bakingapplication.presentation.StepFragmentPresenter
import com.example.android.bakingapplication.presentation.StepFragmentPresenterImpl
import com.example.android.bakingapplication.repository.RecipeRepository

import dagger.Module
import dagger.Provides

@Module
class StepFragmentPresenterModule(private val stepFragmentContext: Context) {

    @Provides
    fun provideStepFragmentPresenter(recipeRepository: RecipeRepository): StepFragmentPresenter {
        return StepFragmentPresenterImpl(stepFragmentContext, recipeRepository)
    }
}
