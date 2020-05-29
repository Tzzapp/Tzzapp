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

        roomX = findViewById(R.id.editTextRoomXK);
        roomY = findViewById(R.id.editTextRoomYK);
        lampX = findViewById(R.id.editTextLampXK);
        lampY = findViewById(R.id.editTextLampYK);
        lampChountX = findViewById(R.id.editTextLampCountXK);
        lampChountY = findViewById(R.id.editTextLampCountYK);

        MathMaster = findViewById(R.id.buttonCalculate);

        responseX = findViewById(R.id.editTextLampX);
        responseY = findViewById(R.id.editTextLampY);
        responseWallX = findViewById(R.id.editTextWallX);
        responseWallY = findViewById(R.id.editTextWallY);

        MathMaster.setOnClickListener(v -> {

            // Y/right side calculations

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


            // X/left side calculations

            int lampChountXInt, roomXInt, lampXInt;

            if (TextUtils.isEmpty(roomX.getText())){// chekking room
                System.out.println("room bredde mangler");
                roomXInt = 0;
            }
            else{
                holder = roomX.getText();
                roomXInt = Integer.parseInt(holder.toString());
            }

            if (TextUtils.isEmpty(lampX.getText())){// chekking lamp
                System.out.println("lampe bredde mangler");
                lampXInt = 0;
            }
            else{
                holder = lampX.getText();
                lampXInt = Integer.parseInt(holder.toString());
            }


            if (TextUtils.isEmpty(lampChountX.getText())){//chekking if lamp count is missing, if so default to 1
                System.out.println("antal lamper mangler");
                lampChountXInt = 1;
            }
            else{
                holder = lampChountX.getText();
                lampChountXInt = Integer.parseInt(holder.toString());
            }

            responseX.setText("" + findLampLamp(roomXInt, lampXInt, lampChountXInt));
            responseWallX.setText("" + findLampWall(roomXInt, lampXInt, lampChountXInt));


        });

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


