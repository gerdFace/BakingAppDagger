package com.example.android.bakingapplication

import android.app.Activity
import android.app.Application
import android.content.Context

import com.example.android.bakingapplication.dagger.component.ApplicationComponent
import com.example.android.bakingapplication.dagger.component.DaggerApplicationComponent
import com.example.android.bakingapplication.dagger.component.StepFragmentSubcomponent
import com.example.android.bakingapplication.dagger.module.AppModule
import com.example.android.bakingapplication.dagger.module.StepFragmentModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BakingApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    lateinit var applicationComponent: ApplicationComponent
    private var stepFragmentSubcomponent: StepFragmentSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = initDagger()
        applicationComponent.inject(this)
    }

    private fun initDagger(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    fun createStepFragmentSubcomponent(stepFragmentContext: Context): StepFragmentSubcomponent {
        return applicationComponent.plus(StepFragmentModule(stepFragmentContext))
                                   .apply { stepFragmentSubcomponent = this }
    }

    fun releaseStepFragmentSubcomponent() {
        stepFragmentSubcomponent = null
    }
}
