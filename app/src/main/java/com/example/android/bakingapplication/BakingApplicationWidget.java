package com.example.android.bakingapplication;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.android.bakingapplication.model.Ingredient;
import com.example.android.bakingapplication.model.RecipeData;
import com.example.android.bakingapplication.view.activity.DetailListActivity;

import javax.inject.Inject;

import io.realm.Realm;

public class BakingApplicationWidget extends AppWidgetProvider{

    @Inject
    Realm realm;

    int lastAccessedRecipeId;

    RecipeData recipe;
    private String TAG = this.getClass().getSimpleName();

    public void setLastAccessedRecipeId(int lastAccessedRecipeId) {
        this.lastAccessedRecipeId = lastAccessedRecipeId;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        ((BakingApplication) context.getApplicationContext()).getApplicationComponent().inject(this);

        if (lastAccessedRecipeId == 0) {
            return;
        }

        recipe = realm.where(RecipeData.class)
                .equalTo("id", lastAccessedRecipeId)
                .findFirst();

        Log.d(TAG, "updateAppWidget: " + recipe.getName());

        Intent intent = new Intent(context, DetailListActivity.class);
        intent.putExtra("recipe_id", lastAccessedRecipeId);
        intent.putExtra("recipe_name", recipe.getName());
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_application_widget);
        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
        views.setTextViewText(R.id.widget_recipe_name, recipe.getName());
        views.setTextViewText(R.id.widget_recipe_ingredients, formatIngredientsForDisplay(recipe));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private String formatIngredientsForDisplay(RecipeData recipe) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Ingredient ingredient : recipe.getIngredients()) {


            Log.d(TAG, "formatIngredientsForDisplay: " + ingredient.getIngredientFormattedForDisplay());
            stringBuilder.append(ingredient.getIngredientFormattedForDisplay());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

