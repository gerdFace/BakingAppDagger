package com.example.android.bakingapplication.dagger.component

import com.example.android.bakingapplication.BakingApplicationWidget
import com.example.android.bakingapplication.dagger.module.*
import com.example.android.bakingapplication.view.activity.DetailPagerActivity
import com.example.android.bakingapplication.view.activity.MainActivity
import com.example.android.bakingapplication.view.fragment.DetailListFragment
import com.example.android.bakingapplication.view.fragment.IngredientsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    MainActivityModule::class,
    DetailPagerActivityModule::class,
    IngredientsFragmentModule::class,
    DetailListFragmentModule::class,
    RealmModule::class
])
interface ApplicationComponent {

    fun inject(target: BakingApplicationWidget)

    fun inject(target: MainActivity)

    fun inject(target: DetailListFragment)

    fun inject(target: DetailPagerActivity)

    fun inject(target: IngredientsFragment)

    fun plus(module: StepFragmentModule): StepFragmentSubcomponent
}
