package com.example.android.bakingapplication.dagger.component

import com.example.android.bakingapplication.dagger.module.StepFragmentModule
import com.example.android.bakingapplication.dagger.scope.PerFragment
import com.example.android.bakingapplication.view.fragment.StepFragment

import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [StepFragmentModule::class])
interface StepFragmentSubcomponent {

    fun inject(target: StepFragment)
}
