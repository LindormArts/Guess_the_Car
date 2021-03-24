package uk.ac.westminster.guessthecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class identifyTheCar extends AppCompatActivity {


    private String solution;
    private ArrayList<String> generatedBrands = new ArrayList<String>();
    private String imgOne;
    private String imgTwo;
    private String imgThree;
    private View NextQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car);


        //calls the hashmap of car image IDs from another class
        Catalog x = new Catalog();
        HashMap<String, List<Integer>> carCatalog = x.carCatalog();

        //generates a list that holds all of the car brands
        List<String> carBrands = new ArrayList<String>(carCatalog.keySet());

        //Generates a random number
        Random rng = new Random();

        //stores the selected car brand (key) into a variable
        String selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));
        imgOne = selectedBrand;
        //creates a new list that stores the brand images
        List<Integer> brandImages = carCatalog.get(selectedBrand);

        //randomly selects a position in the brandImages list
        Integer selectedCar = rng.nextInt(brandImages.size());

        //plugs the selected car image into the imageview for the user
        ImageView v1 = findViewById(R.id.car1);
        v1.setImageResource(brandImages.get(selectedCar));

        //Adds the generated brand to an array list
        generatedBrands.add(selectedBrand);

        //removes it from the list so that that model isn't repeated
        carBrands.remove(selectedBrand);

        selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));
        imgTwo = selectedBrand;
        brandImages = carCatalog.get(selectedBrand);
        selectedCar = rng.nextInt(brandImages.size());


        //plugs the selected car image into the imageview for the user
        ImageView v2 = findViewById(R.id.car2);
        v2.setImageResource(brandImages.get(selectedCar));

        generatedBrands.add(selectedBrand);
        carBrands.remove(selectedBrand);
        selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));
        imgThree = selectedBrand;
        brandImages = carCatalog.get(selectedBrand);
        selectedCar = rng.nextInt(brandImages.size());


        generatedBrands.add(selectedBrand);
        //plugs the selected car image into the imageview for the user
        ImageView v3 = findViewById(R.id.car3);
        v3.setImageResource(brandImages.get(selectedCar));

        //randomly select a position in the arraylist to be the game solution
        Random rand = new Random();
        int pointer = rand.nextInt(generatedBrands.size());
        solution = generatedBrands.get(pointer);
        ((TextView)findViewById(R.id.carBrand)).setText("Click on the: \n" + solution);

        NextQuestion = (Button)findViewById(R.id.nextBtn);
        NextQuestion.setVisibility(View.INVISIBLE);
    }


    public void carOneBtnPressed(View view) {
        //Checks if the car associated with this button matches the solution
        //Toast.makeText(getApplicationContext(),v1,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(),"v1 pressed",Toast.LENGTH_SHORT).show();
        if (imgOne.equals(solution)){
            correct();
        } else{
            incorrect();
        }
    }

    public void carTwoBtnPressed(View view) {
        //Toast.makeText(getApplicationContext(),"v2 pressed",Toast.LENGTH_SHORT).show();
        if (imgTwo.equals(solution)){
            correct();
        } else{
            incorrect();
        }
    }

    public void carThreeBtnPressed(View view) {
        //Toast.makeText(getApplicationContext(),"v3 pressed",Toast.LENGTH_SHORT).show();
        if (imgThree.equals(solution)){
            correct();
        } else{
            incorrect();
        }
    }

    public void correct(){
        //Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();

        //removes the onclick for the images
        View removeImgOne = findViewById(R.id.car1);
        View removeImgTwo = findViewById(R.id.car2);
        View removeImgThree = findViewById(R.id.car3);

        removeImgOne.setOnClickListener(null);
        removeImgTwo.setOnClickListener(null);
        removeImgThree.setOnClickListener(null);


        //Changes the text to correct and green
        ((TextView)findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#00FF00"));
        ((TextView)findViewById(R.id.carBrand)).setText("CORRECT!");
        NextQuestion.setVisibility(View.VISIBLE);

    }

    public void incorrect(){
        //Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();

        //removes the onclick for the images
        View removeImgOne = findViewById(R.id.car1);
        View removeImgTwo = findViewById(R.id.car2);
        View removeImgThree = findViewById(R.id.car3);

        removeImgOne.setOnClickListener(null);
        removeImgTwo.setOnClickListener(null);
        removeImgThree.setOnClickListener(null);

        ((TextView)findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#FF0000"));
        ((TextView)findViewById(R.id.carBrand)).setText("INCORRECT!");
        NextQuestion.setVisibility(View.VISIBLE);
    }

    public void nextTest(View view) {
        //stops the activity and calls it again to reset the game
        Intent intent = getIntent();
        finish();
        startActivity(intent);

    }
}