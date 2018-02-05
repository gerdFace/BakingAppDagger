package com.example.android.bakingapplication.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.android.bakingapplication.model.Step
import com.example.android.bakingapplication.repository.RecipeRepository
import com.example.android.bakingapplication.view.fragment.StepFragmentView
import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class StepFragmentPresenterImpl(private val context: Context, private val recipeRepository: RecipeRepository) : StepFragmentPresenter {

    private var currentStep: Step? = null
    private var player: SimpleExoPlayer? = null
    private var videoIsAvailable: Boolean = false
    private var view: StepFragmentView? = null
    private val TAG = StepFragmentPresenterImpl::class.java.simpleName

    override fun initializeVideoPlayer() {
        videoIsAvailable = !currentStep!!.videoURL.isEmpty()

        if (videoIsAvailable && player == null) {
            val trackSelector = DefaultTrackSelector()

            val loadControl = DefaultLoadControl()

            player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl)

            val playerResumePosition = view!!.getPlayerResumePosition()

            if (playerResumePosition > 0) {
                player!!.seekTo(playerResumePosition)
            }
            player!!.prepare(prepareMediaSource())
        }

        updateUI()
    }

    override fun releaseVideoPlayer() {
        if (player != null) {
            player!!.playWhenReady = false
            player!!.release()
            player = null
        }
    }

    override fun loadStep() {
        val recipeId = view!!.getRecipeId()
        val stepIndex = view!!.getStepIndex()

        recipeRepository.getStep(recipeId, stepIndex, object:RecipeRepository.GetStepCallback {
            override fun onStepLoaded(step: Step) {
                currentStep = step
            }

            override fun onDataNotAvailable(failureMessage: String) {}
        })
    }

    override fun setView(view: StepFragmentView) {
        this.view = view
        loadStep()
        initializeVideoPlayer()
    }

    override fun updateUI() {
        val shortStepDescription = currentStep!!.shortDescription
        val longStepDescription = currentStep!!.description
        val thumbnailIsAvailable = !currentStep!!.thumbnailURL.isEmpty()

        if (videoIsAvailable && view!!.twoPane() && !view!!.twoPane()) {
            view!!.showFullScreenVideoView(player)
        } else if (videoIsAvailable) {
            view!!.showVideoView(player, shortStepDescription, longStepDescription)
        } else if (thumbnailIsAvailable) {
            view!!.showImageViewNoVideo(currentStep!!.thumbnailURL, shortStepDescription, longStepDescription)
        } else {
            view!!.showNoVideoNoImageView(shortStepDescription, longStepDescription)
        }
    }

    override fun pauseVideoPlayer() {
        if (player != null) {
            player!!.playWhenReady = false
        }
    }

    override fun getPlayerPosition(): Long {
            return if (player != null) {
                player!!.currentPosition
            } else {
                0
        }
    }

    private fun prepareMediaSource(): MediaSource {
        val videoUrl = if (!currentStep!!.videoURL.isEmpty()) currentStep!!.videoURL else currentStep!!.thumbnailURL

        val dataSourceFactory = DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "BakingApplication"), null)

        val extractorsFactory = DefaultExtractorsFactory()

        Log.d(TAG, "prepareMediaSource: Media source = " + Uri.parse(videoUrl))

        return ExtractorMediaSource(Uri.parse(videoUrl),
                dataSourceFactory, extractorsFactory, null, null)
    }
}
