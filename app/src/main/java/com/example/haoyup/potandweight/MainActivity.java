package com.example.haoyup.potandweight;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_GETMESSAGE = 110;
    public static final int REQUEST_CODE_SETUPMESSAGE = 100;
    private PotCollection myPots = new PotCollection();
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateListView();
        setupAddButton();
        registerClickCallback();
    }


    private void populateListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.pot_list,
                myPots.getPotDescriptions());

        ListView list = (ListView) findViewById(R.id.potList);
        list.setAdapter(adapter);
    }

    private void setupAddButton() {
        Button btn = (Button) findViewById(R.id.btnAdd);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch AddPotActivity
                Intent intent = AddPotActivity.makeIntent(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_GETMESSAGE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_GETMESSAGE) {
            if (resultCode == Activity.RESULT_OK) {
                // get the intent
                Pot newPot = AddPotActivity.getPotFromIntent(data);

                // add new pot
                myPots.addPot(newPot);

                Toast.makeText(getApplicationContext(), "add", Toast.LENGTH_SHORT)
                        .show();

                populateListView();
            }
        }
        if (requestCode == REQUEST_CODE_SETUPMESSAGE) {
            if (resultCode == Activity.RESULT_OK) {
                // get the intent
                Pot newPot = AddPotActivity.getPotFromIntent(data);

                // edit pot
                myPots.changePot(newPot, pos);
                populateListView();
            }
        }
    }


    private void registerClickCallback() {
        // Calculate
        ListView list = (ListView) findViewById(R.id.potList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View viewClicked, int position, long id) {
//                TextView textview = (TextView) viewClicked;
                Pot clickPot = myPots.getPot(position);
                Intent intent = CalculateAcitivity.makeLaunchIntent(MainActivity.this, clickPot);
                startActivity(intent);
            }
        });

        // Long press to edit pot
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View viewLongClicked, int position, long id) {
                pos = position;
                Intent intent = AddPotActivity.makeIntent(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_SETUPMESSAGE);

                return true;
            }
        });

    }
}
