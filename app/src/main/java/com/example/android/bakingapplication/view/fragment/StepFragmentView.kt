package com.example.android.bakingapplication.view.fragment

import com.google.android.exoplayer2.SimpleExoPlayer

interface StepFragmentView {

    fun showVideoView(player: SimpleExoPlayer?, shortStepDescription: String, longStepDescription: String)

    fun showNoVideoNoImageView(shortStepDescription: String, longStepDescription: String)

    fun showImageViewNoVideo(imageUrl: String, shortStepDescription: String, longStepDescription: String)

    fun showFullScreenVideoView(player: SimpleExoPlayer?)

    fun isLandscapeOrientation(): Boolean

    fun twoPane(): Boolean

    fun getPlayerResumePosition(): Long

    fun getRecipeId(): Int

    fun getStepIndex(): Int
}
