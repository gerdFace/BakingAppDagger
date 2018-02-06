package com.example.android.bakingapplication.view.fragment

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.android.bakingapplication.BakingApplication
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.presentation.StepFragmentPresenter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import javax.inject.Inject

class StepFragment : Fragment(), StepFragmentView {

    @Inject
    lateinit var stepFragmentPresenter: StepFragmentPresenter

    @BindView(R.id.short_step_description)
    lateinit var shortDescriptionView: TextView

    @BindView(R.id.long_step_description)
    lateinit var longDescriptionView: TextView

    @BindView(R.id.image_that_does_not_exist)
    lateinit var imageView: ImageView

    @BindView(R.id.player_view)
    lateinit var simpleExoPlayerView: SimpleExoPlayerView

    @BindView(R.id.fragment_step_constraint_container)
    lateinit var constraintLayout: ConstraintLayout


    private var playerPosition: Long = 0
    private var recipeId: Int = 0
    private var stepIndex: Int = 0

    private val constraintSet = ConstraintSet()
    private val application: BakingApplication
        get() = activity!!.application as BakingApplication

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        application.createStepFragmentComponent(context!!).inject(this)
    }

// View bindings, etc

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_step_with_video, container, false)

        ButterKnife.bind(this, view)

        constraintSet.clone(activity!!.applicationContext, R.layout.fragment_step_no_video)

        if (savedInstanceState != null) {
            stepIndex = savedInstanceState.getInt("step_index")
            recipeId = savedInstanceState.getInt("recipe_id")
            playerPosition = savedInstanceState.getLong("player_resume_position")
        } else {
            stepIndex = arguments!!.getInt(ARG_STEP_INDEX)
            recipeId = arguments!!.getInt(ARG_RECIPE_ID)
            playerPosition = 0
        }

        return view
    }

    override fun onPause() {
        super.onPause()
        playerPosition = stepFragmentPresenter.getPlayerPosition()
        stepFragmentPresenter.releaseVideoPlayer()
    }

    override fun getPlayerResumePosition(): Long {
        return playerPosition
    }

    override fun onResume() {
        super.onResume()
        stepFragmentPresenter.setView(this)
    }

    override fun isLandscapeOrientation(): Boolean {
        val orientation = activity!!.resources.configuration.orientation
        return orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (this.isVisible) {
            if (!isVisibleToUser) {
                stepFragmentPresenter.pauseVideoPlayer()
            } else {
                stepFragmentPresenter.updateUI()
            }
        }
    }

    override fun showVideoView(player: SimpleExoPlayer?, shortStepDescription: String, longStepDescription: String) {
        simpleExoPlayerView.player = player
        simpleExoPlayerView.visibility = View.VISIBLE
        setStepDescriptionViewsVisible()
        setDescriptionText(shortStepDescription, longStepDescription)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun showNoVideoNoImageView(shortStepDescription: String, longStepDescription: String) {
        simpleExoPlayerView.visibility = View.GONE
        setStepDescriptionViewsVisible()
        setDescriptionText(shortStepDescription, longStepDescription)
        TransitionManager.beginDelayedTransition(constraintLayout)
        constraintSet.setVisibility(R.id.player_view, View.GONE)
        constraintSet.applyTo(constraintLayout)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun showImageViewNoVideo(imageUrl: String, shortStepDescription: String, longStepDescription: String) {
        setStepDescriptionViewsVisible()
        shortDescriptionView.text = shortStepDescription
        longDescriptionView.text = longStepDescription
        simpleExoPlayerView.visibility = View.GONE
        Glide.with(context).load(imageUrl).into(imageView)
    }

    override fun showFullScreenVideoView(player: SimpleExoPlayer?) {
        simpleExoPlayerView.player = player
        simpleExoPlayerView.visibility = View.VISIBLE
        shortDescriptionView.visibility = View.GONE
        longDescriptionView.visibility = View.GONE
    }

    override fun twoPane(): Boolean {
        return resources.getBoolean(R.bool.isTablet)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("step_index", stepIndex)
        outState.putInt("recipe_id", recipeId)
        outState.putLong("player_resume_position", playerPosition)
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        stepFragmentPresenter.updateUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        application.releaseStepFragmentComponent()
    }

    private fun setDescriptionText(shortStepDescription: String, longStepDescription: String) {
        shortDescriptionView.text = shortStepDescription

        if (shortStepDescription != longStepDescription) {
            longDescriptionView.text = longStepDescription
        }
    }

    private fun setStepDescriptionViewsVisible() {
        shortDescriptionView.visibility = View.VISIBLE
        longDescriptionView.visibility = View.VISIBLE
    }

    override fun getStepIndex(): Int {
        return stepIndex
    }

    override fun getRecipeId(): Int {
        return recipeId
    }

    companion object {

        private const val ARG_STEP_INDEX = "step_index"
        private const val ARG_RECIPE_ID = "recipe_id"

        fun newInstance(recipeId: Int, stepIndex: Int): StepFragment {
            val args = Bundle()
            args.putInt(ARG_RECIPE_ID, recipeId)
            args.putInt(ARG_STEP_INDEX, stepIndex)

            val stepFragment = StepFragment()
            stepFragment.arguments = args
            return stepFragment
        }
    }
}
