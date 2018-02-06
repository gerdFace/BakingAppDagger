package com.example.android.bakingapplication.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.example.android.bakingapplication.BakingApplication
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.model.Step
import com.example.android.bakingapplication.presentation.DetailPagerActivityPresenter
import com.example.android.bakingapplication.view.activity.DetailListActivity.Companion.ID_OF_RECIPE_SELECTED
import com.example.android.bakingapplication.view.activity.DetailListActivity.Companion.NAME_OF_FOOD_SELECTED
import com.example.android.bakingapplication.view.fragment.StepFragment
import javax.inject.Inject

class DetailPagerActivity : AppCompatActivity(), DetailPagerActivityView {

    @Inject lateinit var detailPagerActivityPresenter: DetailPagerActivityPresenter

    @BindView(R.id.step_view_pager) lateinit var stepViewPager: ViewPager

    private var stepDetailList: List<Step>? = null
    private var nameOfFoodItem: String? = null
    private var stepIndex: Int = 0
    override var recipeId: Int = 0
    private var fragmentStatePagerAdapter: FragmentStatePagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pager)

        (application as BakingApplication).applicationComponent.inject(this)

        ButterKnife.bind(this)

        if (savedInstanceState == null) {
            stepIndex = intent.getIntExtra(STEP_INDEX, 0)
            nameOfFoodItem = intent.getStringExtra(DetailListActivity.NAME_OF_FOOD_SELECTED)
            recipeId = intent.getIntExtra(ID_OF_RECIPE_SELECTED, 0)
        } else {
            stepIndex = savedInstanceState.getInt(STEP_INDEX)
            nameOfFoodItem = savedInstanceState.getString(NAME_OF_FOOD_SELECTED)
            recipeId = savedInstanceState.getInt(ID_OF_RECIPE_SELECTED)
        }

        title = nameOfFoodItem
    }

    private fun setViewPager() {
        val fragmentManager = supportFragmentManager

        if (fragmentStatePagerAdapter == null) {
            fragmentStatePagerAdapter = object : FragmentStatePagerAdapter(fragmentManager) {
                override fun getItem(position: Int): Fragment {
                    return StepFragment.newInstance(recipeId, position)
                }

                override fun getCount(): Int {
                    return stepDetailList!!.size
                }
            }

            stepViewPager.adapter = fragmentStatePagerAdapter
        } else {
            fragmentStatePagerAdapter?.notifyDataSetChanged()
        }

        for (i in stepDetailList!!.indices) {
            if (i == stepIndex) {
                stepViewPager.currentItem = i
                break
            }
        }

        stepViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                stepIndex = position
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    override fun setSteps(steps: List<Step>) {
        stepDetailList = steps
        setViewPager()
    }

    override fun showErrorMessage(failureMessage: String) {
        Log.d("Error loading steps: ", failureMessage)
    }

    override fun onResume() {
        super.onResume()
        detailPagerActivityPresenter.setView(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(ID_OF_RECIPE_SELECTED, recipeId)
        outState?.putInt(STEP_INDEX, stepIndex)
        outState?.putString(NAME_OF_FOOD_SELECTED, nameOfFoodItem)
    }

    companion object {

        private const val STEP_INDEX = "position_of_step_selected"
    }
}
