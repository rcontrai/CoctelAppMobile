package com.pad.coctelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pad.coctelapp.services.RecipeLoaderCallbacks;
import com.pad.coctelapp.ui.RecipeListAdapter;
import com.pad.coctelapp.util.Recipe;

import java.util.ArrayList;
import java.util.List;

/** Activity that launches a search for cocktails on theCocktailDB and then displays
 *  the results.
 */
public class CocktailChoiceActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AmendActivity";
    private static final int RECIPE_LOADER_ID = 0;

    /** the RecyclerView used to display the results*/
    public RecyclerView recyclerView;
    RecipeListAdapter adapter;
    TextView resultsText;
    /** the list of ingredients to use as search terms on theCocktailDB*/
    ArrayList<String> ingredients;
    private RecipeLoaderCallbacks recipeLoaderCallbacks = new RecipeLoaderCallbacks(this);

    @Override
    @SuppressWarnings( "deprecation" )
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG,"CocktailChoiceActivity started");
        setContentView(R.layout.activity_cocktailchoice);

        //Setting up recyclerView
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecipeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        resultsText = findViewById(R.id.cocktailsText);
        setResultsText("Loading...");

        //Getting the list of ingredients to search from the intent that summoned the activity
        Intent intent = getIntent();
        ingredients = intent.getStringArrayListExtra(AmendActivity.EXTRA_ADD_INGREDIENTS);

        LoaderManager loaderManager = LoaderManager.getInstance(this);
        if(loaderManager.getLoader(RECIPE_LOADER_ID) != null) {
            loaderManager.initLoader(RECIPE_LOADER_ID,null, recipeLoaderCallbacks);
        }

        Bundle argsBundle = new Bundle();
        argsBundle.putStringArrayList("ingredients",ingredients);
        getSupportLoaderManager().restartLoader(RECIPE_LOADER_ID, argsBundle, recipeLoaderCallbacks);
    }

    /** Updates the list of recipes displayed by recyclerView
     *
     * @param recipes a list of recipes
     */
    public void updateRecipeList(List<Recipe> recipes) {
        adapter.setRecipes(recipes);
        adapter.notifyDataSetChanged();
    }

    /** Sets the text of the resultsText TextView
     *
     * @param text
     */
    public void setResultsText(String text) {
        resultsText.setText(text);
    }


}
