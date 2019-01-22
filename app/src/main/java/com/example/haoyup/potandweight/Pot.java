package com.example.haoyup.potandweight;

/**
 * Store information about a single pot
 */

public class Pot {

    private String pot_name;
    private int pot_weight;

    // Set member data based on parameters.
    public Pot(String name, int weightInG) {
        pot_name = name;
        pot_weight = weightInG;
    }

    // Return the weight
    public int getWeightInG() {
        return pot_weight;
    }

    // Set the weight. Throws IllegalArgumentException if weight is less than 0.
    public void setWeightInG(int weightInG) {
        if (weightInG < 0){
            throw new IllegalArgumentException();
        }else{
            pot_weight = weightInG;
        }
    }

    // Return the name.
    public String getName() {
        return pot_name;
    }

    // Set the name. Throws IllegalArgumentException if name is an empty string (length 0),
    // or if name is a null-reference.
    public void setName(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException();
        }else{
            pot_name = name;
        }
    }


}
