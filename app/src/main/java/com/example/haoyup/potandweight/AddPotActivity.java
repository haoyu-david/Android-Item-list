package com.example.haoyup.potandweight;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPotActivity extends AppCompatActivity {

    private static Pot newPot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pot);

    setupOkButton();
    setupCancelButton();
    }

    private void setupOkButton() {
        Button btn = (Button) findViewById(R.id.btnOK);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Extra data from UI
                EditText editName =(EditText) findViewById(R.id.messageName);
                String message_name = editName.getText().toString();
                EditText editWeight =(EditText) findViewById(R.id.messageWeight);
                String message_weight = editWeight.getText().toString();
                if (message_name.equals("") || message_weight.equals("")){
                    Toast.makeText(getApplicationContext(), "Invalid data", Toast.LENGTH_SHORT)
                            .show();
                    finish();
                }else {
                    int weight = Integer.parseInt(message_weight);
                    if (weight < 0) {
                        Toast.makeText(getApplicationContext(), "Invalid Weight, please add again!", Toast.LENGTH_SHORT)
                                .show();
                        finish();
                    }
                }

                //Pass data back
                Intent intent = new Intent();
                intent.putExtra("The name", message_name);
                intent.putExtra("The weight", message_weight);
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    private void setupCancelButton() {
        Button btn = (Button) findViewById(R.id.btnCancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AddPotActivity.class);
    }

    public static Pot getPotFromIntent(Intent data){
        int weight = Integer.parseInt(data.getStringExtra("The weight"));
        newPot = new Pot(data.getStringExtra("The name"), weight);
        return newPot;
    }

}
