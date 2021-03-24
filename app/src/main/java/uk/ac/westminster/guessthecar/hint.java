package uk.ac.westminster.guessthecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class hint extends AppCompatActivity {

    private String selectedBrand;
    private EditText userAnswer;
    private String capturedAnswer;
    private String dash;
    private static int count = 0;
    String word;
    char[] dashChars;
    private Integer guessCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);


        //calls the hashmap of car image IDs from another class
        Catalog x = new Catalog();
        HashMap<String, List<Integer>> carCatalog = x.carCatalog();


        //generates a list that holds all of the car brands
        List<String> carBrands = new ArrayList<String>(carCatalog.keySet());

        //Generates a random number
        Random rng = new Random();

        //stores the selected car brand (key) into a variable
        selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));

        //creates a new list that stores the brand images
        List<Integer> brandImages = carCatalog.get(selectedBrand);

        //randomly selects a position in the brandImages list
        Integer selectedCar = rng.nextInt(brandImages.size());

        //plugs the selected car image into the imageview for the user
        ImageView v1 = findViewById(R.id.car1);
        v1.setImageResource(brandImages.get(selectedCar));



        //sets the asterisks where the full word would be
        dash = new String(new char[selectedBrand.length()]).replace("\0", "-");
        ((TextView)findViewById(R.id.word)).setText(dash);

        Toast.makeText(getApplicationContext(),dash, Toast.LENGTH_SHORT).show();

        dashChars = dash.toCharArray();



    }




    public void checkAnswer(View view) {


        //take the user's input
        userAnswer = (EditText)findViewById(R.id.userGuessInput);
        //the user input is captured
        capturedAnswer = userAnswer.getText().toString().toLowerCase();

        //if (guessCount <= 3) {
            //compare input with each position in the word
            for (int i = 0; i < selectedBrand.length(); i++) {
                //if there's a hit, replace the letter with the asterisk
                if (selectedBrand.charAt(i) == capturedAnswer.charAt(0)) {
                    dashChars[i] = capturedAnswer.charAt(0);
                    dash = String.valueOf(dashChars);
                    ((TextView) findViewById(R.id.word)).setText(dash);
                    ((TextView) findViewById(R.id.userGuessInput)).setText("");


                    if ((dash).equals(selectedBrand)) {
                        //((TextView) findViewById(R.id.word)).setText(selectedBrand);
                        Toast.makeText(getApplicationContext(), "win", Toast.LENGTH_SHORT).show();
                        ((TextView) findViewById(R.id.carBrand)).setText("CORRECT");
                        ((TextView) findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#00FF00"));
                        ((Button) findViewById(R.id.button)).setText("Next");
                        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                    }
                }

            }

        /*} else {
            ((TextView) findViewById(R.id.carBrand)).setText("WRONG");
            ((TextView) findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#FF0000"));

            ((Button) findViewById(R.id.button)).setText("Next");
            ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });

        }*/


    }
}