package com.usn.tzzapp;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class Lamps extends AppCompatActivity {

    // innformation inn
    EditText roomSize;
    EditText roomWith;
    EditText lampSize;
    EditText lampWith;
    EditText lampChountSize;
    EditText lampChountWith;


    // button to calculate - replace if posible with auto-update
    Button MathMaster;
    Editable holder;

    // response fields
    EditText responseX;
    EditText responseY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activety_lamps);

        roomSize = findViewById(R.id.editTextRoomLength);
        roomWith = findViewById(R.id.editTextRoomWith);
        lampSize = findViewById(R.id.editTextLampLength);
        lampWith = findViewById(R.id.editTextLampWith);
        lampChountSize = findViewById(R.id.editTextLampCountLength);
        lampChountWith = findViewById(R.id.editTextLampCountWith);

        MathMaster = findViewById(R.id.buttonCalculate);

        responseX = findViewById(R.id.editTextLampX);
        responseY = findViewById(R.id.editTextLampY);

        MathMaster.setOnClickListener(v -> {
            //Log.d("WORDS", "" + roomSize.getText());

                responseX.setText(findLampXLamp() + "");

        });


        //Button buttonKalkuler = findViewById(R.id.buttonKalkuler);

       // final Intent intentLamps = new Intent(this, LampKalkulert.class);

       // buttonKalkuler.setOnClickListener(v -> startActivity(intentLamps));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    private int findLampXLamp(){
    holder = roomSize.getText();

       int out;

       out= Integer.parseInt(holder.toString());

        holder = lampSize.getText();
        int lampX = Integer.parseInt(holder.toString());

        holder = lampChountSize.getText();
        int lampsX = Integer.parseInt(holder.toString());

        //subtract total used by lamps
        out = out - (lampX * lampsX);

        // divide by number of lamps to find an equal distance betwen them
        out = out / lampsX;

        //Log.d("test", out +" ");


        //Log.d("WORDS", "in method: " + room.toString());
        return out;
    }
}


