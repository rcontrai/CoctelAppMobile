package com.pad.coctelapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AmendActivity extends AppCompatActivity {

    private static final String TAG = "AmendActivity";
    static final String EXTRA_ADD_INGREDIENTS = "es.ucm.fdi.calculator.EXTRA_ADD_INGREDIENTS";

    RecyclerView recyclerView;
    IngredientListAdapter adapter;

    List<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"AmendActivity started");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_amend_p);
        } else {
            setContentView(R.layout.activity_amend_l);
        }


        recyclerView = findViewById(R.id.recyclerView);
        adapter = new IngredientListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* Test display*/
        try {
            ingredients = new ArrayList<String>();
            ingredients.add("Vodka");
            ingredients.add("Lager");
            ingredients.add("Blue Curacao");
            ingredients.add("Cider");
            this.updateIngredientList(ingredients);
        } catch (Exception e) {
            e.printStackTrace();
        }/**/

    }

    public void updateIngredientList(List<String> ingredients) {
        adapter.setIngredients(ingredients);
        adapter.notifyDataSetChanged();
    }

    public void startCocktailSearch(View view) {
        Log.d(TAG,"searchCocktailButton was pressed");
        Intent checkRecipesIntent = new Intent(this,CocktailChoiceActivity.class);
        ArrayList<CharSequence> charSequenceIngredients = new ArrayList<CharSequence>(ingredients.size());
        charSequenceIngredients.addAll(ingredients);
        checkRecipesIntent.putCharSequenceArrayListExtra(EXTRA_ADD_INGREDIENTS,charSequenceIngredients);
        Log.d(TAG,"launching CocktailChoiceActivity");
        startActivity(checkRecipesIntent);
    }
}
