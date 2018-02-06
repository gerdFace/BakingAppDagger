package com.example.android.bakingapplication.dagger.module

import com.example.android.bakingapplication.view.fragment.DetailListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @ContributesAndroidInjector(modules = [DetailListFragmentModule::class])
    abstract fun bingDetailListFragmentInjector(): DetailListFragment
}