package com.pad.coctelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takePhoto(View view) {
        Log.d(TAG,"photoButton was pressed");
        Intent checkIngredientsIntent = new Intent(this,AmendActivity.class);
        Log.d(TAG,"launching AmendActivity");
        startActivity(checkIngredientsIntent);
    }
}
