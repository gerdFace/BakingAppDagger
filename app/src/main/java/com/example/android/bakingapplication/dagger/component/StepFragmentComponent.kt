package com.example.android.bakingapplication.dagger.component

import com.example.android.bakingapplication.dagger.module.StepFragmentPresenterModule
import com.example.android.bakingapplication.dagger.scope.PerFragment
import com.example.android.bakingapplication.view.fragment.StepFragment

import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [StepFragmentPresenterModule::class])
interface StepFragmentComponent {

    fun inject(target: StepFragment)
}
