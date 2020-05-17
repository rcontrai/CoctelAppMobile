package com.pad.coctelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/** Activity that handles the welcome screen.
 */
public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** The onClick method for photoButton
     *  Launches AmendActivity
     *  Planned : should allow the user to take a photo/choose an image from the file system
     * @param view
     */
    public void takePhoto(View view) {
        Log.d(LOG_TAG,"photoButton was pressed");
        Intent checkIngredientsIntent = new Intent(this,AmendActivity.class);
        Log.d(LOG_TAG,"launching AmendActivity");
        startActivity(checkIngredientsIntent);
    }
}
