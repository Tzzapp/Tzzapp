package com.usn.tzzapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class Lamps extends AppCompatActivity {

    // innformation inn
    EditText roomX;
    EditText roomY;
    EditText lampX;
    EditText lampY;
    EditText lampChountX;
    EditText lampChountY;


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

        roomX = findViewById(R.id.editTextRoomLength);
        roomY = findViewById(R.id.editTextRoomWith);
        lampX = findViewById(R.id.editTextLampLength);
        lampY = findViewById(R.id.editTextLampWith);
        lampChountX = findViewById(R.id.editTextLampCountLength);
        lampChountY = findViewById(R.id.editTextLampCountWith);

        MathMaster = findViewById(R.id.buttonCalculate);

        responseX = findViewById(R.id.editTextLampX);
        responseY = findViewById(R.id.editTextLampY);
        responseWallX = findViewById(R.id.editTextWallX);
        responseWallY = findViewById(R.id.editTextWallY);

        MathMaster.setOnClickListener(v -> {
            //Log.d("WORDS", "" + roomSize.getText());

            int lampChountYInt, roomYInt, lampYInt;

            if (TextUtils.isEmpty(roomY.getText())){// chekking room
                System.out.println("room bredde mangler");
                roomYInt = 0;
            }
            else{
                holder = roomY.getText();
                roomYInt = Integer.parseInt(holder.toString());
            }

            if (TextUtils.isEmpty(lampY.getText())){// chekking lamp
                System.out.println("lampe bredde mangler");
                lampYInt = 0;
            }
            else{
                holder = lampY.getText();
                lampYInt = Integer.parseInt(holder.toString());
            }


            if (TextUtils.isEmpty(lampChountY.getText())){//chekking if lamp count is missing, if so default to 1
                System.out.println("antal lamper mangler");
                lampChountYInt = 1;
            }
            else{
                holder = lampChountY.getText();
                lampChountYInt = Integer.parseInt(holder.toString());
            }

            responseY.setText("" + findLampLamp(roomYInt, lampYInt, lampChountYInt));
            responseWallY.setText("" + findLampWall(roomYInt, lampYInt, lampChountYInt));



/*
            // gather teh varius lamp data
            holder = roomX.getText();
            int roomXInt = Integer.parseInt(holder.toString());

            holder = lampX.getText();
            int lampXInt = Integer.parseInt(holder.toString());

            holder = lampChountX.getText();
            int lampChountXInt = Integer.parseInt(holder.toString());




            //sjekk at verken rom eller lampe er manglende - om de er gi respons




            // hvis lampen er bredere en rommet gi respons
            if (lampYInt * lampChountXInt > roomYInt){
                System.out.println("lampene er bredere en rommet");
            }








            if (!TextUtils.isEmpty(lampChountX.getText())){
                responseX.setText(findLampXLamp() + "");
            }
            else{
                responseX.setText("N/A");
            }

            if (findLampXWall() >= 0) {
                responseWallX.setText(findLampXWall() + "");
            }
            else {
                responseWallX.setText("N/A");
            }
            */

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
       holder = roomX.getText();
       if (TextUtils.isEmpty(holder)){
           return 0;
        }

       out= Integer.parseInt(holder.toString());
       System.out.println(out + " fra romm-størelse");

        holder = lampX.getText();
        if (TextUtils.isEmpty(holder)){
            return 0;
        }
        int lampX = Integer.parseInt(holder.toString());
        System.out.println(lampX + " fra lampX");

        holder = lampChountX.getText();
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


