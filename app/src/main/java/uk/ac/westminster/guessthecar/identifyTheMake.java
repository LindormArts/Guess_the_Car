package uk.ac.westminster.guessthecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class identifyTheMake extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String selectedBrand;
    String userAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_make);

        //creates our spinner
        Spinner spinner = findViewById(R.id.make_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.carMakes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

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



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //creating a variable that stores the user's answer
        userAnswer = parent.getItemAtPosition(position).toString();



        //testing the selection works with a toast
        //Toast.makeText(parent.getContext(), userAnswer, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    public void checkAnswer(View view) {
        //Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),selectedBrand,Toast.LENGTH_SHORT).show();
        if (userAnswer.toLowerCase().equals(selectedBrand)) {
            correct();
        }
        else {
            incorrect();
        }
    }



    public void correct() {
        //Changes the text to correct and green
        ((TextView)findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#00FF00"));
        ((TextView)findViewById(R.id.carBrand)).setText("CORRECT!");
        //Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
        //Changes the button to say next, and allows the user to reset the activity
        ((Button)findViewById(R.id.button)).setText("Next");
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }


    private void incorrect() {
        //Changes the text to incorrect and changes the colour
        ((TextView)findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#FF0000"));
        ((TextView)findViewById(R.id.carBrand)).setText("INCORRECT!");
        ((TextView)findViewById(R.id.word)).setTextColor(Color.parseColor("#FFFF00"));
        ((TextView)findViewById(R.id.word)).setText("The correct answer is: " + selectedBrand);
        //Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();
        //Changes the button to say next, and allows the user to reset the activity
        ((Button)findViewById(R.id.button)).setText("Next");
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });
    }


}