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

    // information inn
    EditText roomX;
    EditText roomY;
    EditText lampX;
    EditText lampY;
    EditText lampCountX;
    EditText lampCountY;

    // icontainment boxes
    TextInputLayout roomXTextInputLayout;
    TextInputLayout roomYTextInputLayout;
    TextInputLayout lampXTextInputLayout;
    TextInputLayout lampYTextInputLayout;
    TextInputLayout lampCountXTextInputLayout;
    TextInputLayout lampCountYTextInputLayout;


    // button to calculate - replace if possible with auto-update
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
        setContentView(R.layout.activity_lamps);

        setTitle(getString(R.string.lamps));

        roomX = findViewById(R.id.editTextRoomXK);
        roomY = findViewById(R.id.editTextRoomYK);
        lampX = findViewById(R.id.editTextLampXK);
        lampY = findViewById(R.id.editTextLampYK);
        lampCountX = findViewById(R.id.editTextLampCountXK);
        lampCountY = findViewById(R.id.editTextLampCountYK);

        MathMaster = findViewById(R.id.buttonCalculate);

        responseX = findViewById(R.id.editTextLampX);
        responseY = findViewById(R.id.editTextLampY);
        responseWallX = findViewById(R.id.editTextWallX);
        responseWallY = findViewById(R.id.editTextWallY);

        roomXTextInputLayout = findViewById(R.id.textfieldRoomXK);
        roomYTextInputLayout = findViewById(R.id.textfieldRoomYK);
        lampXTextInputLayout = findViewById(R.id.textfieldLampXK);
        lampYTextInputLayout = findViewById(R.id.textfieldLampYK);
        lampCountXTextInputLayout = findViewById(R.id.textfieldLampCountXK);
        lampCountYTextInputLayout = findViewById(R.id.textfieldLampCountYK);

        disableEditors();

        MathMaster.setOnClickListener(v -> {

            // Y/right side calculations

            int lampCountYInt, roomYInt, lampYInt;

            if (TextUtils.isEmpty(roomY.getText())){// checking room
                roomYTextInputLayout.setError(getString(R.string.error_room_y));
                roomYInt = 0;
            }
            else{
                holder = roomY.getText();
                roomYInt = Integer.parseInt(holder.toString());
                roomYTextInputLayout.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(lampY.getText())){// checking lamp
                lampYTextInputLayout.setError(getString(R.string.error_lamp_y));
                lampYInt = 0;
            }
            else{
                holder = lampY.getText();
                lampYInt = Integer.parseInt(holder.toString());
                lampYTextInputLayout.setErrorEnabled(false);
            }


            if (TextUtils.isEmpty(lampCountY.getText())){//checking if lamp count is missing, if so default to 1
                lampCountYTextInputLayout.setError(getString(R.string.error_lamp_count));
                lampCountYInt = 1;
            }
            else{
                holder = lampCountY.getText();
                lampCountYInt = Integer.parseInt(holder.toString());
                lampCountYTextInputLayout.setErrorEnabled(false);
            }

            responseY.setText("" + findLampLamp(roomYInt, lampYInt, lampCountYInt));
            responseWallY.setText("" + findLampWall(roomYInt, lampYInt, lampCountYInt));


            // X/left side calculations

            int lampCountXInt, roomXInt, lampXInt;

            if (TextUtils.isEmpty(roomX.getText())){// checking room
                roomXTextInputLayout.setError(getString(R.string.error_room_x));
                roomXInt = 0;
            }
            else{
                holder = roomX.getText();
                roomXInt = Integer.parseInt(holder.toString());
                roomXTextInputLayout.setErrorEnabled(false);
            }

            if (TextUtils.isEmpty(lampX.getText())){// checking lamp
                lampXTextInputLayout.setError(getString(R.string.error_lamp_x));
                lampXInt = 0;
            }
            else{
                holder = lampX.getText();
                lampXInt = Integer.parseInt(holder.toString());
                lampXTextInputLayout.setErrorEnabled(false);
            }


            if (TextUtils.isEmpty(lampCountX.getText())){//checking if lamp count is missing, if so default to 1
                lampCountXTextInputLayout.setError(getString(R.string.error_lamp_count));
                lampCountXInt = 1;
            }
            else{
                holder = lampCountX.getText();
                lampCountXInt = Integer.parseInt(holder.toString());
                lampCountXTextInputLayout.setErrorEnabled(false);
            }

            responseX.setText("" + findLampLamp(roomXInt, lampXInt, lampCountXInt));
            responseWallX.setText("" + findLampWall(roomXInt, lampXInt, lampCountXInt));


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private int findLampLamp(int room, int lamp, int lampCount){
        int out = room; // take the length of the room
        out = out - (lamp * lampCount); // subtract the collected amount "used up" by the lamps
        out = out / lampCount; // divide the remainder by the amount of lamps, giving an equivalent distance between them
        return out;
    }

    private int findLampWall(int room, int lamp, int lampCount){
        return (findLampLamp(room, lamp, lampCount) / 2);
    }

    private void disableEditors(){
        responseX.setKeyListener(null);
        responseY.setKeyListener(null);
        responseWallX.setKeyListener(null);
        responseWallY.setKeyListener(null);
    }

}


