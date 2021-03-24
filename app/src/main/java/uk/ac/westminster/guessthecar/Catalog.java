package uk.ac.westminster.guessthecar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Catalog {
    //Creates a hash map to store all the cars and their manufacturers as their keys

    HashMap<String, List<Integer>> carCatalog = new HashMap<String, List<Integer>>();

    public HashMap<String, List<Integer>> carCatalog() {
        carCatalog.put("bmw", Arrays.asList(R.drawable.bmw1, R.drawable.bmw2, R.drawable.bmw3));
        carCatalog.put("tesla", Arrays.asList(R.drawable.tesla1, R.drawable.tesla2, R.drawable.tesla3));
        carCatalog.put("ford", Arrays.asList(R.drawable.ford1, R.drawable.ford2, R.drawable.ford3));
        carCatalog.put("audi", Arrays.asList(R.drawable.audi1, R.drawable.audi2, R.drawable.audi3));
        carCatalog.put("honda", Arrays.asList(R.drawable.honda1, R.drawable.honda2, R.drawable.honda3));
        carCatalog.put("nissan", Arrays.asList(R.drawable.nissan1, R.drawable.nissan2, R.drawable.nissan3));
        carCatalog.put("porsche", Arrays.asList(R.drawable.porsche1, R.drawable.porsche2, R.drawable.porsche3));
        carCatalog.put("mitsubishi", Arrays.asList(R.drawable.mitsubishi1, R.drawable.mitsubishi2, R.drawable.mitsubishi3));
        carCatalog.put("maserati", Arrays.asList(R.drawable.maserati1, R.drawable.maserati2, R.drawable.maserati3));
        carCatalog.put("jaguar", Arrays.asList(R.drawable.jaguar1, R.drawable.jaguar2, R.drawable.jaguar3));

        return carCatalog;

    }

}
