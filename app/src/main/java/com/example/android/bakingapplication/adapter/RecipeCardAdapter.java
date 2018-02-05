package com.example.android.bakingapplication.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.android.bakingapplication.R;
import com.example.android.bakingapplication.model.RecipeData;
import java.util.List;


public class RecipeCardAdapter extends RecyclerView.Adapter<RecipeCardAdapter.RecipeViewHolder> {

    private static final String TAG = RecipeCardAdapter.class.getSimpleName();
    private final RecipeCardAdapterOnClickHandler recipeClickHandler;
    private Context context;
    private List<RecipeData> recipeList;
    private int[] thumbnails = new int[] {
            R.drawable.nutella_pie,
            R.drawable.brownies,
            R.drawable.yellow_cake,
            R.drawable.cheesecake};

    public interface RecipeCardAdapterOnClickHandler {
        void onRecipeSelected(String nameOfFoodSelected, int foodID);
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dessertName;
        TextView numberOfSteps;
        TextView numberOfIngredients;
        TextView numberOfServings;
        ImageView thumbnail;

        RecipeViewHolder(View view) {
            super(view);
            dessertName = (TextView) view.findViewById(R.id.dessert_name);
            numberOfSteps = (TextView) view.findViewById(R.id.number_of_steps);
            numberOfIngredients = (TextView) view.findViewById(R.id.number_of_ingredients);
            numberOfServings = (TextView) view.findViewById(R.id.number_of_servings);
            thumbnail = (ImageView) view.findViewById(R.id.recipe_image);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int positionOfRecipeSelected = getAdapterPosition();
            int foodID = recipeList.get(positionOfRecipeSelected).getId();
            String nameOfFoodSelected = recipeList.get(positionOfRecipeSelected).getName();
            recipeClickHandler.onRecipeSelected(nameOfFoodSelected, foodID);
        }
    }

    public RecipeCardAdapter(Context mContext, List<RecipeData> recipeList, RecipeCardAdapterOnClickHandler recipeClickHandler) {
        this.context = mContext;
        this.recipeList = recipeList;
        this.recipeClickHandler = recipeClickHandler;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecipeViewHolder holder, int position) {
        RecipeData recipe = recipeList.get(position);
        holder.dessertName.setText(recipe.getName());
        holder.numberOfSteps.setText(String.format("%s %s", context.getResources().getString(R.string.steps), recipe.getSteps().size()));
        holder.numberOfIngredients.setText(String.format("%s %s", context.getResources().getString(R.string.ingredients), recipe.getIngredients().size()));
        holder.numberOfServings.setText(String.format("%s %s", context.getResources().getString(R.string.servings), recipe.getServings().toString()));

        Log.d(TAG, "Glide fetching thumbnail from: " + recipe.getImage());
        Glide.with(context).load(thumbnails[position]).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

}

