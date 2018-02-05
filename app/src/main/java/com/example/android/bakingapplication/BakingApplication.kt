package com.example.android.bakingapplication

import android.app.Application
import android.content.Context

import com.example.android.bakingapplication.dagger.component.ApplicationComponent
import com.example.android.bakingapplication.dagger.component.DaggerApplicationComponent
import com.example.android.bakingapplication.dagger.component.StepFragmentComponent
import com.example.android.bakingapplication.dagger.module.AppModule
import com.example.android.bakingapplication.dagger.module.StepFragmentPresenterModule

class BakingApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    private var stepFragmentComponent: StepFragmentComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initDagger()
    }

    private fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    fun createStepFragmentComponent(stepFragmentContext: Context): StepFragmentComponent {
        return applicationComponent.plus(StepFragmentPresenterModule(stepFragmentContext))
    }

    fun releaseStepFragmentComponent() {
        stepFragmentComponent = null
    }
}
