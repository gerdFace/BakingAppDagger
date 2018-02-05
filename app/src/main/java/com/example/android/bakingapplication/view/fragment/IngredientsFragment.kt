package com.example.android.bakingapplication.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.example.android.bakingapplication.BakingApplication
import com.example.android.bakingapplication.R
import com.example.android.bakingapplication.adapter.IngredientsAdapter
import com.example.android.bakingapplication.model.Ingredient
import com.example.android.bakingapplication.presentation.IngredientsFragmentPresenter
import javax.inject.Inject

class IngredientsFragment : Fragment(), IngredientsFragmentView {

    @Inject
    lateinit var ingredientsFragmentPresenter: IngredientsFragmentPresenter

    @BindView(R.id.rv_ingredient_list)
    lateinit var rvIngredientList: RecyclerView

    private var recipeId: Int = 0

    private var ingredientAdapter: IngredientsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeId = savedInstanceState?.getInt(ARG_RECIPE_ID_KEY) ?: arguments!!.getInt(ARG_RECIPE_ID_KEY)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_ingredient_list, container, false)

        (activity!!.application as BakingApplication).applicationComponent.inject(this)

        ButterKnife.bind(this, view)

        val layoutManager = LinearLayoutManager(this.activity, LinearLayout.VERTICAL, false)

        rvIngredientList.layoutManager = layoutManager

        return view
    }

    override fun onResume() {
        super.onResume()
        ingredientsFragmentPresenter.setView(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_RECIPE_ID_KEY, recipeId)
    }

    override fun showIngredients(ingredientList: List<Ingredient>) {
        if (ingredientAdapter == null) {
            ingredientAdapter = IngredientsAdapter(ingredientList)
            rvIngredientList.adapter = ingredientAdapter
        } else {
            ingredientAdapter!!.notifyDataSetChanged()
        }
    }

    override fun showErrorMessage(failureMessage: String) {
        Log.d("Failed ingredients: ", failureMessage)
    }

    override fun getRecipeId(): Int {
        return recipeId
    }

    companion object {

        private const val ARG_RECIPE_ID_KEY = "recipe_id_key"

        fun newInstance(recipeId: Int): IngredientsFragment {
            val args = Bundle()
            args.putInt(ARG_RECIPE_ID_KEY, recipeId)

            val ingredientsFragment = IngredientsFragment()
            ingredientsFragment.arguments = args
            return ingredientsFragment
        }
    }
}
