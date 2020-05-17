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

/** Activity that displays the drinks that were identified previously for review by the user.\n
 *  Planned : should allow them to edit the list if they find it wrong.
 */
@SuppressWarnings("Convert2Diamond")
public class AmendActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AmendActivity";
    static final String EXTRA_ADD_INGREDIENTS = "es.ucm.fdi.calculator.EXTRA_ADD_INGREDIENTS";

    RecyclerView recyclerView;
    IngredientListAdapter adapter;
    /** the list of ingredients to be displayed for review by the user and possibly edited */
    public ArrayList<String> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG,"AmendActivity started");
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_amend_p);
        } else {
            setContentView(R.layout.activity_amend_l);
        }

        //Setting up recyclerView
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

    /** Updates the list of ingredients displayed by recyclerView
     *
     * @param ingredients a list of strings representing ingredients
     */
    public void updateIngredientList(List<String> ingredients) {
        adapter.setIngredients(ingredients);
        adapter.notifyDataSetChanged();
    }

    /** onClick method for searchCocktailButton
     *  launches CocktailChoiceActivity and passes the list o ingredients to search to it
     *
     * @param view
     */
    public void startCocktailSearch(View view) {
        Log.d(LOG_TAG,"searchCocktailButton was pressed");
        Intent checkRecipesIntent = new Intent(this,CocktailChoiceActivity.class);
        /*ArrayList<CharSequence> charSequenceIngredients = new ArrayList<CharSequence>(ingredients.size());
        charSequenceIngredients.addAll(ingredients);
        checkRecipesIntent.putCharSequenceArrayListExtra(EXTRA_ADD_INGREDIENTS,charSequenceIngredients);*/
        checkRecipesIntent.putStringArrayListExtra(EXTRA_ADD_INGREDIENTS,ingredients);
        Log.d(LOG_TAG,"launching CocktailChoiceActivity");
        startActivity(checkRecipesIntent);
    }
}
