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

public class advancedLevel extends AppCompatActivity {



    private Integer imgOne;
    private Integer imgTwo;
    private Integer imgThree;

    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private ArrayList<String> generatedCars = new ArrayList<String>();

    private EditText userAnswerOne;
    private String capturedAnswerOne;

    private EditText userAnswerTwo;
    private String capturedAnswerTwo;

    private EditText userAnswerThree;
    private String capturedAnswerThree;

    private Boolean correctOne = false;
    private Boolean correctTwo = false;
    private Boolean correctThree = false;

    private Integer guessCount = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_level);

        //calls a method that sets the images
        selectImgOne();



    }

    public void selectImgOne(){
        //calls the hashmap of car image IDs from another class
        Catalog x = new Catalog();
        HashMap<String, List<Integer>> carCatalog = x.carCatalog();

        //generates a list that holds all of the car brands
        List<String> carBrands = new ArrayList<String>(carCatalog.keySet());

        //Generates a random number
        Random rng = new Random();

        //stores the selected car brand (key) into a variable
        String selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));

        answerOne = selectedBrand;

        //creates a new list that stores the brand images
        List<Integer> brandImages = carCatalog.get(selectedBrand);

        //randomly selects a position in the brandImages list
        Integer selectedCar = rng.nextInt(brandImages.size());
        imgOne = selectedCar;



        //plugs the selected car image into the imageview for the user
        ImageView v1 = findViewById(R.id.car1);
        v1.setImageResource(brandImages.get(selectedCar));


        //calls the function that generates the next car image
        selectImgTwo();
    }

    public void selectImgTwo(){
        Catalog x = new Catalog();
        HashMap<String, List<Integer>> carCatalog = x.carCatalog();
        List<String> carBrands = new ArrayList<String>(carCatalog.keySet());
        Random rng = new Random();
        String selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));
        answerTwo = selectedBrand;
        List<Integer> brandImages = carCatalog.get(selectedBrand);
        Integer selectedCar = rng.nextInt(brandImages.size());
        imgTwo = selectedCar;

        //checks if the other image views have the same image ids.
        //If so, the method is called again to generate another image
        if (imgOne == imgTwo){
            selectImgTwo();
        }else{
            ImageView v1 = findViewById(R.id.car2);
            v1.setImageResource(brandImages.get(selectedCar));
            selectImgThree();
        }

    }

    public void selectImgThree(){
        Catalog x = new Catalog();
        HashMap<String, List<Integer>> carCatalog = x.carCatalog();
        List<String> carBrands = new ArrayList<String>(carCatalog.keySet());
        Random rng = new Random();
        String selectedBrand = carBrands.get(rng.nextInt(carBrands.size()));
        answerThree = selectedBrand;
        List<Integer> brandImages = carCatalog.get(selectedBrand);
        Integer selectedCar = rng.nextInt(brandImages.size());
        imgThree = selectedCar;

        if (imgOne == imgThree || imgTwo == imgThree){
            selectImgThree();
        }else{
            ImageView v1 = findViewById(R.id.car3);
            v1.setImageResource(brandImages.get(selectedCar));
            //Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();
        }

    }

    public void checkAnswers(View view) {
        if (guessCount <= 3) {
            //Toast.makeText(getApplicationContext(),answerOne,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),answerTwo,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),answerThree,Toast.LENGTH_SHORT).show();


            //gets the text input
            userAnswerOne = (EditText) findViewById(R.id.carOneInput);
            capturedAnswerOne = userAnswerOne.getText().toString().toLowerCase();

            userAnswerTwo = (EditText) findViewById(R.id.carTwoInput);
            capturedAnswerTwo = userAnswerTwo.getText().toString().toLowerCase();

            userAnswerThree = (EditText) findViewById(R.id.carThreeInput);
            capturedAnswerThree = userAnswerThree.getText().toString().toLowerCase();

            //Toast.makeText(getApplicationContext(),capturedAnswerOne,Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),imgOne,Toast.LENGTH_SHORT).show();

            //compares text input to solutions
            if ((capturedAnswerOne).equals(answerOne)) {
                //if they match then change text to green and make uneditable
                //Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carOneInput)).setTextColor(Color.parseColor("#00FF00"));
                ((EditText) findViewById(R.id.carOneInput)).setEnabled(false);
                correctOne = true;



            } else {
                //if not, change them to red
                //Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carOneInput)).setTextColor(Color.parseColor("#FF0000"));
                guessCount++;
            }


            if ((capturedAnswerTwo).equals(answerTwo)) {
                //Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carTwoInput)).setTextColor(Color.parseColor("#00FF00"));
                ((EditText) findViewById(R.id.carTwoInput)).setEnabled(false);
                correctTwo = true;
            } else {
                //Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carTwoInput)).setTextColor(Color.parseColor("#FF0000"));
                guessCount++;
            }


            if ((capturedAnswerThree).equals(answerThree)) {
                //Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carThreeInput)).setTextColor(Color.parseColor("#00FF00"));
                ((EditText) findViewById(R.id.carThreeInput)).setEnabled(false);
                correctThree = true;
            } else {
                //Toast.makeText(getApplicationContext(),"incorrect",Toast.LENGTH_SHORT).show();
                ((EditText) findViewById(R.id.carThreeInput)).setTextColor(Color.parseColor("#FF0000"));
                guessCount++;
            }


            //if all are correct, allow the user to try again
            if ((correctOne == true) & (correctTwo == true) & (correctThree == true)) {
                //Changes the button to say next, and allows the user to reset the activity
                ((Button) findViewById(R.id.nextBtn)).setText("Next");
                ((Button) findViewById(R.id.nextBtn)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
            }
        } else {
            ((TextView) findViewById(R.id.carBrand)).setText("WRONG");
            ((TextView) findViewById(R.id.carBrand)).setTextColor(Color.parseColor("#FF0000"));

            if ((correctOne == false) & (correctTwo == false) & (correctThree == false)) {
                ((TextView) findViewById(R.id.carOneInput)).setTextColor(Color.parseColor("#FFFF00"));
                ((EditText) findViewById(R.id.carOneInput)).setText(answerOne);

                ((TextView) findViewById(R.id.carTwoInput)).setTextColor(Color.parseColor("#FFFF00"));
                ((EditText) findViewById(R.id.carTwoInput)).setText(answerTwo);

                ((TextView) findViewById(R.id.carThreeInput)).setTextColor(Color.parseColor("#FFFF00"));
                ((EditText) findViewById(R.id.carThreeInput)).setText(answerThree);
            }

            ((EditText) findViewById(R.id.carOneInput)).setEnabled(false);
            ((EditText) findViewById(R.id.carTwoInput)).setEnabled(false);
            ((EditText) findViewById(R.id.carThreeInput)).setEnabled(false);

            ((Button) findViewById(R.id.nextBtn)).setText("Next");
            ((Button) findViewById(R.id.nextBtn)).setOnClickListener(new View.OnClickListener() {
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