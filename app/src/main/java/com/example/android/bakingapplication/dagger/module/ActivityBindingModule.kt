package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.view.activity.DetailListActivity
import com.example.android.bakingapplication.view.activity.DetailPagerActivity
import com.example.android.bakingapplication.view.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule  {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivityInjector(): MainActivity

    @ContributesAndroidInjector(modules = [DetailPagerActivityModule::class])
    abstract fun bindDetailPagerActivityInjector(): DetailPagerActivity

    @ContributesAndroidInjector
    abstract fun bindDetailListActivityInjector(): DetailListActivity
}
