package com.example.android.bakingapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.view.fragment.DetailListFragment
import com.example.android.bakingapplication.view.fragment.StepFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class DetailListActivity : AppCompatActivity(), DetailListFragment.DetailItemCallbacks {

    private var recipeName: String? = null
    private var recipeId: Int = 0
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master_detail)

        if (savedInstanceState != null) {
            recipeName = savedInstanceState.getString(SAVED_RECIPE_NAME)
            recipeId = savedInstanceState.getInt(SAVED_RECIPE_ID, 1)
        } else {
            recipeName = intent.getStringExtra(NAME_OF_FOOD_SELECTED)
            recipeId = intent.getIntExtra(ID_OF_RECIPE_SELECTED, 1)
            val detailListFragment = DetailListFragment.newInstance(recipeId)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.detail_list_container, detailListFragment)
                    .commit()
        }

        twoPane = resources.getBoolean(R.bool.isTablet)

        if (twoPane) {
            loadStepFragmentForTablet(0)
        }

        title = recipeName
    }

    override fun onRecipeDetailButtonClicked(position: Int) {
        if (!twoPane) {
            val bundle = Bundle()
            bundle.putInt(POSITION_OF_STEP_SELECTED, position)
            bundle.putString(NAME_OF_FOOD_SELECTED, recipeName)
            bundle.putInt(ID_OF_RECIPE_SELECTED, recipeId)

            val intentToStartDetailPagerActivity = Intent(this, DetailPagerActivity::class.java)
            intentToStartDetailPagerActivity.putExtras(bundle)
            startActivity(intentToStartDetailPagerActivity)

        } else {
            loadStepFragmentForTablet(position)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString(SAVED_RECIPE_NAME, recipeName)
        outState.putInt(SAVED_RECIPE_ID, recipeId)
    }

    private fun loadStepFragmentForTablet(position: Int) {
        val instructionFragment = StepFragment.newInstance(recipeId, position)

        supportFragmentManager.beginTransaction()
                .replace(R.id.ingredient_and_instruction_container, instructionFragment)
                .commit()
    }

    companion object {

        const val NAME_OF_FOOD_SELECTED = "name_of_recipe_selected"
        const val ID_OF_RECIPE_SELECTED = "id_of_recipe_selected"
        private const val POSITION_OF_STEP_SELECTED = "position_of_step_selected"
        private const val SAVED_RECIPE_NAME = "saved_recipe_name"
        private const val SAVED_RECIPE_ID = "saved_recipe_id"
    }
}
