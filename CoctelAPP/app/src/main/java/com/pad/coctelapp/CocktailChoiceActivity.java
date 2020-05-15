package com.pad.coctelapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CocktailChoiceActivity extends AppCompatActivity {

    private static final String TAG = "AmendActivity";

    RecyclerView recyclerView;
    IngredientListAdapter adapter; //Temporary, should be replaced by a class dedicated to cocktail lists

    List<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"CocktailChoiceActivity started");
        setContentView(R.layout.activity_cocktailchoice);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new IngredientListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        ArrayList<CharSequence> charSequenceIngredients
                = intent.getCharSequenceArrayListExtra(AmendActivity.EXTRA_ADD_INGREDIENTS);
        ingredients = new ArrayList<String>(charSequenceIngredients.size());
        for (CharSequence ingr : charSequenceIngredients) {
            ingredients.add((String) ingr);
        }
        /* Test display*/
        try {
            this.updateIngredientList(ingredients);
        } catch (Exception e) {
            e.printStackTrace();
        }/**/
    }

    public void updateIngredientList(List<String> ingredients) { //Temporary, should be replaced by something handling cocktail lists
        adapter.setIngredients(ingredients);
        adapter.notifyDataSetChanged();
    }
}