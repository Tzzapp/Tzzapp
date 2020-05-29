package com.usn.tzzapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Lamps extends AppCompatActivity {

    // innformation inn
    EditText roomX;
    EditText roomY;
    EditText lampX;
    EditText lampY;
    EditText lampChountX;
    EditText lampChountY;

    // icontainment boxes
    TextInputLayout roomXTextInputLayout;
    TextInputLayout roomYTextInputLayout;
    TextInputLayout lampXTextInputLayout;
    TextInputLayout lampYTextInputLayout;
    TextInputLayout lampChountXTextInputLayout;
    TextInputLayout lampChountYTextInputLayout;


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

        roomXTextInputLayout = findViewById(R.id.textfieldRoomXK);
        roomYTextInputLayout = findViewById(R.id.textfieldRoomYK);
        lampXTextInputLayout = findViewById(R.id.textfieldLampXK);
        lampYTextInputLayout = findViewById(R.id.textfieldLampYK);
        lampChountXTextInputLayout = findViewById(R.id.textfieldLampCountXK);
        lampChountYTextInputLayout = findViewById(R.id.textfieldLampCountYK);

        MathMaster.setOnClickListener(v -> {

            // Y/right side calculations

            int lampChountYInt, roomYInt, lampYInt;

            if (TextUtils.isEmpty(roomY.getText())){// chekking room
                roomYTextInputLayout.setError("room bredde mangler");
                roomYInt = 0;
            }
            else{
                holder = roomY.getText();
                roomYInt = Integer.parseInt(holder.toString());
                roomYTextInputLayout.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(lampY.getText())){// chekking lamp
                lampYTextInputLayout.setError("lampe bredde mangler");
                lampYInt = 0;
            }
            else{
                holder = lampY.getText();
                lampYInt = Integer.parseInt(holder.toString());
                lampYTextInputLayout.setErrorEnabled(false);
            }


            if (TextUtils.isEmpty(lampChountY.getText())){//chekking if lamp count is missing, if so default to 1
                lampChountYTextInputLayout.setError("antal lamper mangler");
                lampChountYInt = 1;
            }
            else{
                holder = lampChountY.getText();
                lampChountYInt = Integer.parseInt(holder.toString());
                lampChountYTextInputLayout.setErrorEnabled(false);
            }

            responseY.setText("" + findLampLamp(roomYInt, lampYInt, lampChountYInt));
            responseWallY.setText("" + findLampWall(roomYInt, lampYInt, lampChountYInt));


            // X/left side calculations

            int lampChountXInt, roomXInt, lampXInt;

            if (TextUtils.isEmpty(roomX.getText())){// chekking room
                roomXTextInputLayout.setError("room lengde mangler");
                roomXInt = 0;
            }
            else{
                holder = roomX.getText();
                roomXInt = Integer.parseInt(holder.toString());
                roomXTextInputLayout.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(lampX.getText())){// chekking lamp
                lampXTextInputLayout.setError("lampe lengde mangler");
                lampXInt = 0;
            }
            else{
                holder = lampX.getText();
                lampXInt = Integer.parseInt(holder.toString());
                lampXTextInputLayout.setErrorEnabled(false);
            }


            if (TextUtils.isEmpty(lampChountX.getText())){//chekking if lamp count is missing, if so default to 1
                System.out.println("antal lamper mangler");
                lampXTextInputLayout.setErrorEnabled(false);
                lampChountXInt = 1;
            }
            else{
                holder = lampChountX.getText();
                lampChountXInt = Integer.parseInt(holder.toString());
                lampXTextInputLayout.setErrorEnabled(false);
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


}


