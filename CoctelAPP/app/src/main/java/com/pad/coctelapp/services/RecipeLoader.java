package com.pad.coctelapp.services;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.pad.coctelapp.util.Recipe;

import java.util.List;

public class RecipeLoader extends AsyncTaskLoader<List<Recipe>> {

    private List<String> mIngredients;

    public RecipeLoader(@NonNull Context context, @NonNull List<String> ingredients) {
        super(context);
        mIngredients = ingredients;
    }

    @Nullable
    @Override
    public List<Recipe> loadInBackground() {
        return CocktailSearcher.findRecipe(mIngredients);
    }

    public void onStartLoading() {
        forceLoad();
    }
}
