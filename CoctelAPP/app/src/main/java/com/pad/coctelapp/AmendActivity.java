package com.pad.coctelapp;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AmendActivity extends AppCompatActivity {

    private static final String TAG = "AmendActivity";

    RecyclerView recyclerView;
    IngredientListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            List<String> ingredients = new ArrayList<String>();
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
}
