package com.example.android.bakingapplication.repository;

import android.support.annotation.NonNull;

import com.example.android.bakingapplication.model.Ingredient;
import com.example.android.bakingapplication.model.RecipeData;
import com.example.android.bakingapplication.model.Step;

import java.util.List;

public interface RecipeRepository {

    interface LoadRecipesCallback {

        void onRecipesLoaded(List<RecipeData> recipes);

        void onDataNotAvailable(String failureMessage);
    }

    interface GetRecipeCallback {

        void onRecipeLoaded(RecipeData recipe);

        void onDataNotAvailable(String failureMessage);
    }

    interface GetStepsCallback {

        void onStepsLoaded(List<Step> steps);

        void onDataNotAvailable(String failureMessage);
    }

    interface GetStepCallback {

        void onStepLoaded(Step step);

        void onDataNotAvailable(String failureMessage);
    }

    interface GetIngredientsCallback {

        void onIngredientsLoaded(List<Ingredient> ingredients);

        void onDataNotAvailable(String failureMessage);
    }

    void getRecipes(@NonNull LoadRecipesCallback callback);

    void getRecipe(int recipeId, @NonNull GetRecipeCallback callback);

    void getSteps(int recipeId, @NonNull GetStepsCallback callback);

    void getStep(int recipeId, int stepIndex, @NonNull GetStepCallback callback);

    void getIngredients(int recipeId, @NonNull GetIngredientsCallback callback);

    void saveRecipesToDatabase(List<RecipeData> recipes);
}
