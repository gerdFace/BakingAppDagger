package com.example.android.bakingapplication;

import com.example.android.bakingapplication.model.Ingredient;
import com.example.android.bakingapplication.model.RecipeData;
import com.example.android.bakingapplication.model.Step;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import io.realm.RealmList;
import kotlin.collections.CollectionsKt;

public class MockRecipeData {

    @NotNull
    private final Step step1 = new Step();
    @NotNull
    private final Step step2 = new Step();
    @NotNull
    private final Step step3 = new Step();
    @NotNull
    private final Step step4 = new Step();
    @NotNull
    private final Ingredient ingredient1 = new Ingredient();
    @NotNull
    private final Ingredient ingredient2 = new Ingredient();
    @NotNull
    private final Ingredient ingredient3 = new Ingredient();
    @NotNull
    private final Ingredient ingredient4 = new Ingredient();
    @NotNull
    private final RecipeData recipe1 = new RecipeData();
    @NotNull
    private final RecipeData recipe2 = new RecipeData();
    @Nullable
    private RealmList<Step> stepList1 = new RealmList<>();
    @Nullable
    private RealmList<Step> stepList2 = new RealmList<>();
    @Nullable
    private RealmList<Ingredient> ingredientList1 = new RealmList<>();
    @Nullable
    private RealmList<Ingredient> ingredientList2 = new RealmList<>();
    @NotNull
    private final List<RecipeData> recipeList;

    @NotNull
    public final List<RecipeData> getRecipeList() {
        return this.recipeList;
    }

    public final void createRecipeList() {
        this.recipeList.clear();
        this.setupMockIngredients();
        this.setupMockSteps();
        this.setupMockRecipes();
    }

    public final void setupMockIngredients() {
        ingredient1.setIngredient("eggs");
        ingredient1.setMeasure("unit");
        ingredient1.setQuantity(3.5F);

        ingredient2.setIngredient("milk");
        ingredient2.setMeasure("cup");
        ingredient2.setQuantity(2.0F);

        ingredient3.setIngredient("cream cheese");
        ingredient3.setMeasure("oz");
        ingredient3.setQuantity(3.0f);

        ingredient4.setIngredient("butter");
        ingredient4.setMeasure("TBLSP");
        ingredient4.setQuantity(1000.0f);

        ingredientList1.add(ingredient1);
        ingredientList1.add(ingredient2);

        ingredientList2.add(ingredient3);
        ingredientList2.add(ingredient4);
    }

    public final void setupMockSteps() {
        step1.setId(0);
        step1.setShortDescription("Something short and sweet");
        step1.setDescription("Here\'s a big long description. It\'s such a serious description. My God! What a description!");
        step1.setThumbnailURL("https://www.youtube.com/watch?v=BB0DU4DoPP4");
        step1.setVideoURL("");

        step2.setId(1);
        step2.setShortDescription("Shorty");
        step2.setDescription("Long, Long, Long, Long!");
        step2.setThumbnailURL("");
        step2.setVideoURL("https://www.youtube.com/watch?v=1cQh1ccqu8M");

        stepList1.add(step1);
        stepList1.add(step2);

        step3.setId(2);
        step3.setShortDescription("Short description");
        step3.setDescription("Long description...............................");
        step3.setThumbnailURL("");
        step3.setVideoURL("https://www.youtube.com/watch?v=_1hgVcNzvzY");

        step4.setId(3);
        step4.setShortDescription("Shor descr");
        step4.setDescription("LONG DESCRRIPPTIONN");
        step4.setThumbnailURL("");
        step4.setVideoURL("https://www.youtube.com/watch?v=VbgT70vuyAQ");

        stepList2.add(step3);
        stepList2.add(step4);
    }

    public final void setupMockRecipes() {
        recipe1.setId(0);
        recipe1.setImage(String.valueOf(2130837607));
        recipe1.setName("Indiana Sugar Cream Pie");
        recipe1.setIngredients(ingredientList1);
        recipe1.setServings(200);
        recipe1.setSteps(stepList1);

        recipe2.setId(1);
        recipe2.setImage(String.valueOf(2130837607));
        recipe2.setName("Mashed Potato Fudge");
        recipe2.setIngredients(ingredientList2);
        recipe2.setServings(200);
        recipe2.setSteps(stepList2);

        recipeList.add(recipe1);
        recipeList.add(recipe2);
    }

    MockRecipeData() {
        this.recipeList = CollectionsKt.mutableListOf(this.recipe1, this.recipe2);
    }
}



