package com.example.android.bakingapplication.repository.local;

import android.support.annotation.NonNull;

import com.example.android.bakingapplication.model.Ingredient;
import com.example.android.bakingapplication.model.RecipeData;
import com.example.android.bakingapplication.model.Step;
import com.example.android.bakingapplication.repository.RecipeRepository;

import java.util.List;

import io.realm.Realm;

public class LocalDataSource implements RecipeRepository {

    private Realm realm;

    public LocalDataSource(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void getRecipes(@NonNull LoadRecipesCallback callback) {
        List<RecipeData> recipes;
        recipes = realm.where(RecipeData.class).findAll();

        if (realm.isEmpty() || recipes.isEmpty()) {
            callback.onDataNotAvailable("Could not load recipe list from database");
        } else {
            callback.onRecipesLoaded(recipes);
        }

    }

    @Override
    public void getRecipe(int recipeId, @NonNull GetRecipeCallback callback) {
        RecipeData recipe;
        recipe = realm.where(RecipeData.class)
                .equalTo("id", recipeId)
                .findFirst();

        if (recipe == null) {
            callback.onDataNotAvailable("Could not load recipe from database");
        } else {
            callback.onRecipeLoaded(recipe);
        }

    }

    @Override
    public void getSteps(int recipeId, @NonNull GetStepsCallback callback) {
        List<Step> steps;
        steps = realm.where(RecipeData.class)
                .equalTo("id", recipeId)
                .findFirst()
                .getSteps();

        if (steps == null) {
            callback.onDataNotAvailable("Could not load step list from database");
        } else {
            callback.onStepsLoaded(steps);
        }

    }

    @Override
    public void getStep(int recipeId, int stepIndex, @NonNull GetStepCallback callback) {

    }

    @Override
    public void getIngredients(int recipeId, @NonNull GetIngredientsCallback callback) {
        List<Ingredient> ingredients;
        ingredients = realm.where(RecipeData.class)
                .equalTo("id", recipeId)
                .findFirst()
                .getIngredients();

        if (ingredients == null) {
            callback.onDataNotAvailable("Could not load ingredient list from database");
        } else {
            callback.onIngredientsLoaded(ingredients);
        }
    }

    @Override
    public void saveRecipesToDatabase(List<RecipeData> recipes) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(recipes);
        realm.commitTransaction();
    }
}
