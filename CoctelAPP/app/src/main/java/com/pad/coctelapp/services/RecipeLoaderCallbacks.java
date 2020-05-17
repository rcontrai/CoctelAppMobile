package com.pad.coctelapp.services;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.pad.coctelapp.CocktailChoiceActivity;
import com.pad.coctelapp.util.Recipe;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Convert2Diamond")
public class RecipeLoaderCallbacks implements LoaderManager.LoaderCallbacks<List<Recipe>> {

    private final static String LOG_TAG = "RecipeLoaderCallbacks";
    CocktailChoiceActivity caller;

    public RecipeLoaderCallbacks(CocktailChoiceActivity caller) {
        this.caller = caller;
    }

    @NonNull
    @Override
    public Loader<List<Recipe>> onCreateLoader(int id, @Nullable Bundle args) {
        ArrayList<String> ingredients = null;

        if (args != null) {
            ingredients = args.getStringArrayList("ingredients");
        }
        Log.d(LOG_TAG,"Just created a RecipeLoader");
        return new RecipeLoader(this.caller, ingredients);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Recipe>> loader, List<Recipe> data) {
        Log.d(LOG_TAG,"Load finished");


    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Recipe>> loader) {

    }
}
