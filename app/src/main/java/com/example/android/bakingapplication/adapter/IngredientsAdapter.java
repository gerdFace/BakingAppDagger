package com.example.android.bakingapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapplication.R;
import com.example.android.bakingapplication.model.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    private List<Ingredient> ingredientsList;

        public IngredientsAdapter(List<Ingredient> ingredientList) {
            this.ingredientsList = ingredientList;
        }

        @Override
        public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient, parent, false);
            return new IngredientsViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final IngredientsViewHolder holder, int position) {
            String ingredient = ingredientsList.get(position).getIngredientFormattedForDisplay();
            holder.ingredients.setText(ingredient);
        }

        @Override
        public int getItemCount() {
            return ingredientsList.size();
        }

        class IngredientsViewHolder extends RecyclerView.ViewHolder {
            public TextView ingredients;

            IngredientsViewHolder(View view) {
                super(view);
                ingredients = (TextView) view.findViewById(R.id.textview_single_ingredient);
            }
        }
}
