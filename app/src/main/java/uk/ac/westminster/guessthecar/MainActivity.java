package uk.ac.westminster.guessthecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchIdentifyMakeActivity(View view) {
        //a test to ensure the button works correctly
        //Log.d(LOG_TAG, "Button clicked!");

        //creates an intent to call the activity
        Intent intent = new Intent(this, identifyTheMake.class);

        //launches the class
        startActivity(intent);
    }

    public void launchHints(View view) {
        //creates an intent to call the activity
        Intent intent = new Intent(this, hint.class);

        //launches the class
        startActivity(intent);
    }

    public void launchIdentifyTheCar(View view) {
        //creates an intent to call the activity
        Intent intent = new Intent(this, identifyTheCar.class);

        //launches the class
        startActivity(intent);
    }

    public void launchAdvancedLevel(View view) {
        //creates an intent to call the activity
        Intent intent = new Intent(this, advancedLevel.class);

        //launches the class
        startActivity(intent);
    }
}