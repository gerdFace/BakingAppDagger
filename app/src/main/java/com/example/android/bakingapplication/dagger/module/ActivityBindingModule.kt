package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule  {

    @ContributesAndroidInjector
    abstract fun bindMainActivityInjector(): MainActivity
}
