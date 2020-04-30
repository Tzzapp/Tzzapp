package com.usn.tzzapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
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
    EditText responseWallX;
    EditText responseWallY;


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
        responseWallX = findViewById(R.id.editTextWallX);
        responseWallY = findViewById(R.id.editTextWallY);

        MathMaster.setOnClickListener(v -> {
            //Log.d("WORDS", "" + roomSize.getText());

            //sjekk at verken rom eller lampe er manglende - om de er gi respons

            //sjekk at der er minst 1 lampe, om det ikke er det set svaret til 1 lampe


            if (!TextUtils.isEmpty(lampChountSize.getText())){
                responseX.setText(findLampXLamp() + "");
            }
            else{
                responseX.setText("N/A");
            }

            if (findLampXWall() > 0) {
                responseWallX.setText(findLampXWall() + "");
            }
            else {
                responseWallX.setText("N/A");
            }

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

    private int findLampLamp(int room, int lamp, int lampChount){
        int out = room; // take the lenght of the room
        out = out - (lamp * lampChount); // subtract the colected amount "used upp" buy the lamps
        out = out / lampChount; // divide the remainder buy the amount of laps, giving an equivalent distance betwen them
        return out;
    }

    private int findLampWall(int room, int lamp, int lampChount){
        return (findLampLamp(room, lamp, lampChount) / 2);
    }

    // 1 of 2 methods used for difrent room-dimentions, chosen instead of taking the data as inn-paramaters to reduce the number of times the application needs to transfer information from EditText and back
    private int findLampXLamp(){


       int out;
       holder = roomSize.getText();
       if (TextUtils.isEmpty(holder)){
           return 0;
        }

       out= Integer.parseInt(holder.toString());
       System.out.println(out + " fra romm-størelse");

        holder = lampSize.getText();
        if (TextUtils.isEmpty(holder)){
            return 0;
        }
        int lampX = Integer.parseInt(holder.toString());
        System.out.println(lampX + " fra lampX");

        holder = lampChountSize.getText();
        int lampCountX;

        if (!TextUtils.isEmpty(holder)) {
            lampCountX = Integer.parseInt(holder.toString());
            System.out.println(lampCountX + " fra lampCountX");
        }
        else{
            lampCountX = 1;
        }

        //subtract total used by lamps
        out = out - (lampX * lampCountX);
        System.out.println(out + " rom minus plass brukt av lamper");

        // divide by number of lamps to find an equal distance betwen them
        out = out / (lampCountX); // fjern fra svaret om der er bare 1
        System.out.println(out + " distanse mellom lamper");

        System.out.println(out/2 + " distanse mellom lamper og vegg");


        //Log.d("test", out +" ");


        //Log.d("WORDS", "in method: " + room.toString());
        return out;
    }

    private int findLampXWall(){
        return findLampXLamp()/2;
    }
}


