package com.example.android.bakingapplication.view.fragment

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.example.android.bakingapplication.BakingApplication
import com.example.android.bakingapplication.BakingApplicationWidget
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.adapter.DetailListAdapter
import com.example.android.bakingapplication.model.Step
import com.example.android.bakingapplication.presentation.DetailListFragmentPresenter
import javax.inject.Inject

class DetailListFragment : Fragment(), DetailListFragmentView {

    @Inject lateinit var detailListFragmentPresenter: DetailListFragmentPresenter

    @BindView(R.id.rv_detail_list) lateinit var rvDetailList: RecyclerView

    @BindView(R.id.ingredient_card_container) lateinit var ingredientCardContainer: CardView

    @BindView(R.id.fragment_detail_list_constraint_container)
    lateinit var constraintLayout: ConstraintLayout

    @BindView(R.id.ingredient_fragment_container)
    lateinit var ingredientFrameLayout: FrameLayout

    override var recipeId: Int = 0
    private var isIngredientFragmentAttached: Boolean = false
    private var callbacks: DetailItemCallbacks? = null
    private var detailListAdapter: DetailListAdapter? = null
    private val constraintSet = ConstraintSet()

    // Interface that enables fragment to communicate with host activity
    interface DetailItemCallbacks {
        fun onRecipeDetailButtonClicked(position: Int)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detail_list_before, container, false)

        ButterKnife.bind(this, view)

        if (savedInstanceState == null) {
            recipeId = arguments!!.getInt(RECIPE_ID_KEY, 0)
            isIngredientFragmentAttached = false
        } else {
            recipeId = savedInstanceState.getInt(RECIPE_ID_KEY)
            isIngredientFragmentAttached = savedInstanceState.getBoolean(IS_INGREDIENT_FRAGMENT_ATTACHED)
        }

        updateViewForIngredientList()

        return view
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity?.application as BakingApplication).applicationComponent.inject(this)

        val layoutManager = LinearLayoutManager(this.activity, LinearLayoutManager.VERTICAL, false)

        rvDetailList.layoutManager = layoutManager

        ingredientCardContainer.setOnClickListener { updateViewForIngredientList() }
    }

    override fun showSteps(steps: List<Step>) {
        if (detailListAdapter == null) {
            detailListAdapter = DetailListAdapter(callbacks)
            detailListAdapter!!.updateDetailListAdapter(steps)

            rvDetailList.adapter = detailListAdapter
        } else {
            detailListAdapter!!.updateDetailListAdapter(steps)
        }
    }

    override fun updateWidgets() {
        val context = activity
        val bakingApplicationWidget = BakingApplicationWidget()
        bakingApplicationWidget.setLastAccessedRecipeId(recipeId)
        val ids = AppWidgetManager.getInstance(context).getAppWidgetIds(ComponentName(context!!, BakingApplicationWidget::class.java))
        bakingApplicationWidget.onUpdate(context, AppWidgetManager.getInstance(context), ids)
    }

    // Ensure that host activity implements the callback interface
    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            callbacks = context as DetailItemCallbacks?
        } catch (e: ClassCastException) {
            throw ClassCastException(context!!.toString() + " must implement OnImageClickListener")
        }

    }

    // Reset callback when fragment detaches from host activity
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onResume() {
        super.onResume()
        detailListFragmentPresenter.setView(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RECIPE_ID_KEY, recipeId)
        outState.putBoolean(IS_INGREDIENT_FRAGMENT_ATTACHED, !isIngredientFragmentAttached)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private fun updateConstraintLayout(layoutId: Int) {
        constraintSet.clone(activity!!.applicationContext, layoutId)
        TransitionManager.beginDelayedTransition(constraintLayout)
        constraintSet.applyTo(constraintLayout)
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private fun updateViewForIngredientList() {
        if (isIngredientFragmentAttached) {
            val ingredientsFragment = IngredientsFragment.newInstance(recipeId)

            activity!!.supportFragmentManager.beginTransaction()
                    .add(R.id.ingredient_fragment_container, ingredientsFragment)
                    .addToBackStack(null)
                    .commit()

            updateConstraintLayout(R.layout.fragment_detail_list_after)

            isIngredientFragmentAttached = false
        } else {
            updateConstraintLayout(R.layout.fragment_detail_list_before)
            isIngredientFragmentAttached = true
        }
    }

    companion object {

        private val RECIPE_ID_KEY = "recipe_id"
        private val IS_INGREDIENT_FRAGMENT_ATTACHED = "is_ingredient_fragment_attached"

        fun newInstance(recipeId: Int): DetailListFragment {
            val args = Bundle()
            args.putInt(RECIPE_ID_KEY, recipeId)

            val detailListFragment = DetailListFragment()
            detailListFragment.arguments = args
            return detailListFragment
        }
    }
}


