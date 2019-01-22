package com.example.haoyup.potandweight;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateAcitivity extends AppCompatActivity {

    private static final String EXTRA_POT = "com.example.haoyup.potandweight - potName";
    private static final String EXTRA_WEIGHT = "com.example.haoyup.potandweight - potWeight";

    private String potName;
    private int weight;
    private int foodWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        extractDataFromIntent();
        setupBackButton();
        updateText();
        calculateFoodWeight();
        calculateServing();
    }



    private void extractDataFromIntent() {
        Intent intent = getIntent();
        potName = intent.getStringExtra(EXTRA_POT);
        weight = intent.getIntExtra(EXTRA_WEIGHT, 0);
    }


    public static Intent makeLaunchIntent(Context context, Pot pot){
        Intent intent = new Intent(context, CalculateAcitivity.class);
        intent.putExtra(EXTRA_POT, pot.getName());
        intent.putExtra(EXTRA_WEIGHT, pot.getWeightInG());

        return intent;
    }

    private void setupBackButton() {
        Button btn = (Button) findViewById(R.id.btnBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateText() {
        TextView potTextView = (TextView) findViewById(R.id.potName);
        String showPot = potName;
        potTextView.setText(showPot);
        TextView weightTextView = (TextView) findViewById(R.id.weightEmpty);
        int showWeightEmpty = weight;
        weightTextView.setText("" + showWeightEmpty);
    }

    private void calculateFoodWeight() {
        final EditText editWeight = (EditText) findViewById(R.id.weightWithFood);
        editWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    int weightPotFood = Integer.parseInt(editWeight.getText().toString());
                    if (weightPotFood < 0) {
                        Toast.makeText(getApplicationContext(), "Invalid Weight", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        foodWeight = weightPotFood - weight;
                        TextView weightTextView = (TextView) findViewById(R.id.weightOfFood);
                        weightTextView.setText("" + foodWeight);
                    }
                }
            }
        });
    }

    private void calculateServing() {
        final EditText editServing = (EditText) findViewById(R.id.numServing);
        editServing.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!editable.toString().equals("")) {
                    int num = Integer.parseInt(editServing.getText().toString());
                    if (num <= 0) {
                        Toast.makeText(getApplicationContext(), "Invalid number of servings", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        int servingW = foodWeight / num;
                        TextView servingTextView = (TextView) findViewById(R.id.servingWeight);
                        servingTextView.setText("" + servingW);
                    }
                }
            }
        });

    }

}
