package com.example.android.bakingapplication.repository.remote;

import com.example.android.bakingapplication.model.RecipeData;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {
    @GET("/topher/2017/May/5907926b_baking/baking.json")
    Call<List<RecipeData>> getRecipes();
}
