package com.example.android.bakingapplication.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.android.bakingapplication.BakingApplication
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.adapter.RecipeCardAdapter
import com.example.android.bakingapplication.model.RecipeData
import com.example.android.bakingapplication.presentation.MainActivityPresenter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), RecipeCardAdapter.RecipeCardAdapterOnClickHandler, MainActivityView {

    private var layoutManagerSavedState: Parcelable? = null

    @Inject
    lateinit var mainActivityPresenter: MainActivityPresenter

    @BindView(R.id.rv_recipe_list)
    lateinit var recipeCardRecyclerView: RecyclerView

    @BindView(R.id.connectivity_error_message)
    lateinit var connectivityErrorMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as BakingApplication).applicationComponent.inject(this)

        ButterKnife.bind(this)
    }

    override fun onRecipeSelected(recipeName: String, recipeId: Int) {
        val recipeDetailActivityDestination = DetailListActivity::class.java
        val openRecipeDetailActivity = Intent(this, recipeDetailActivityDestination)
        openRecipeDetailActivity.putExtra("id_of_recipe_selected", recipeId)
        openRecipeDetailActivity.putExtra("name_of_recipe_selected", recipeName)
        startActivity(openRecipeDetailActivity)
    }

    override fun showRecipes(recipeList: List<RecipeData>) {
        val numberOfColumns = if (!resources.getBoolean(R.bool.isTablet)) 1 else 2

        val layoutManager = GridLayoutManager(this, numberOfColumns)
        recipeCardRecyclerView.layoutManager = layoutManager

        val recipeCardAdapter = RecipeCardAdapter(this, recipeList, this)
        recipeCardRecyclerView.adapter = recipeCardAdapter

        restoreLayoutManagerPosition()
    }

    override fun showErrorMessage(errorMessage: String) {
        if (!mainActivityPresenter.isDeviceOnline(this)) {
            connectivityErrorMessage.visibility = View.VISIBLE
            recipeCardRecyclerView.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivityPresenter.setView(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (layoutManagerSavedState != null) {
            outState!!.putParcelable("saved_layout", recipeCardRecyclerView.layoutManager.onSaveInstanceState())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        layoutManagerSavedState = savedInstanceState.getParcelable("saved_layout")
        super.onRestoreInstanceState(savedInstanceState)
    }

    private fun restoreLayoutManagerPosition() {
        if (layoutManagerSavedState != null) {
            recipeCardRecyclerView.layoutManager.onRestoreInstanceState(layoutManagerSavedState)
        }
    }
}