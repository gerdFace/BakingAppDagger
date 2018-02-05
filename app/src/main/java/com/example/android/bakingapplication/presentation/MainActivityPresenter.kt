package com.example.android.bakingapplication.presentation

import android.content.Context

import com.example.android.bakingapplication.view.activity.MainActivityView

interface MainActivityPresenter {

    fun loadRecipes()

    fun isDeviceOnline(context: Context): Boolean

    fun setView(view: MainActivityView)
}
