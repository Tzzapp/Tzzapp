package com.usn.tzzapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class Heating extends AppCompatActivity {

    EditText ariaX;
    EditText ariaY;
    EditText ariaTotal;
    EditText cableLenght;
    EditText responseCc; //cc is the profecjonal term for the distance betwen difrent sections of the cable as it bends

    Button MathMaster;
    Editable holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ariaX = findViewById(R.id.editTextAriaX);
        ariaY = findViewById(R.id.editTextAriaY);
        ariaTotal = findViewById(R.id.editTextAriaTotal);
        cableLenght = findViewById(R.id.editTextCableLength);
        responseCc = findViewById(R.id.editTextCc);
        responseCc.setKeyListener(null);


        MathMaster = findViewById(R.id.buttonCalculate);



        MathMaster.setOnClickListener(v -> {

            double aria = 0.0, cable = 0.0;

            // if the ariaTotal variable is empty then try ariaX and ariaY
            if (TextUtils.isEmpty(ariaTotal.getText())){

                if (TextUtils.isEmpty(ariaX.getText())){
                    // "an aria is needed"
                }
                else{
                    if (TextUtils.isEmpty(ariaY.getText())){
                        // "an aria is needed"
                        aria = 0.0;
                    }
                    else{
                        holder = ariaY.getText();
                        double ariaYDouble = Integer.parseInt(holder.toString());
                        holder = ariaX.getText();
                        double ariaXDouble = Integer.parseInt(holder.toString());

                        aria = ariaYDouble * ariaXDouble;
                    }
                }
            }
            else{
                if (TextUtils.isEmpty(cableLenght.getText())){
                    // "cable lenght is required
                    cable = 0.0;
                }
                else{
                    holder = cableLenght.getText();
                    cable = Integer.parseInt(holder.toString());
                }
                responseCc.setText("" + (aria / cable));
            }

        });
    }
/*
    private void disableEditors(){
        responseX.setKeyListener(null);
        responseY.setKeyListener(null);
        responseWallX.setKeyListener(null);
        responseWallY.setKeyListener(null);
    }*/
}