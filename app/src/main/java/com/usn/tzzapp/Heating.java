package com.usn.tzzapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Heating extends AppCompatActivity {

    EditText ariaX;
    EditText ariaY;
    EditText ariaTotal;
    EditText cableLenght;
    EditText responseCc; //cc is the profecjonal term for the distance betwen difrent sections of the cable as it bends

    TextInputLayout ariaXTextInputLayout;
    TextInputLayout ariaYTextInputLayout;
    TextInputLayout ariaTotalTextInputLayout;
    TextInputLayout cableLenghtTextInputLayout;

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

        ariaXTextInputLayout = findViewById(R.id.textfieldAriaX);
        ariaYTextInputLayout = findViewById(R.id.textfieldAriaY);
        ariaTotalTextInputLayout = findViewById(R.id.textfieldAriaTotal);
        cableLenghtTextInputLayout = findViewById(R.id.textfieldCableLength);


        MathMaster = findViewById(R.id.buttonCalculate);



        MathMaster.setOnClickListener(v -> {
            double cable;
            double aria;

            cableLenghtTextInputLayout.setErrorEnabled(false);
            ariaTotalTextInputLayout.setErrorEnabled(false);
            ariaYTextInputLayout.setErrorEnabled(false);
            ariaXTextInputLayout.setErrorEnabled(false);

            // ||

            if (TextUtils.isEmpty(ariaTotal.getText())){

                if (TextUtils.isEmpty(ariaX.getText())){

                    if (TextUtils.isEmpty(ariaY.getText())){
                        ariaTotalTextInputLayout.setError(getString(R.string.area_missing_error));
                        responseCc.setText("-----");
                        if (TextUtils.isEmpty(cableLenght.getText())){
                            cableLenghtTextInputLayout.setError(getString(R.string.cable_missing_error));
                            responseCc.setText("-----");
                        }
                    }
                    else{
                        ariaXTextInputLayout.setError(getString(R.string.room_x_missing_error));
                        responseCc.setText("-----");
                    }

                }
                else{
                    if (TextUtils.isEmpty(ariaY.getText())){
                        ariaYTextInputLayout.setError(getString(R.string.room_y_missing_error));
                        responseCc.setText("-----");
                    }
                    else{
                        if (TextUtils.isEmpty(cableLenght.getText())){
                            cableLenghtTextInputLayout.setError(getString(R.string.cable_missing_error));
                            responseCc.setText("-----");
                        }
                        else{
                            holder = ariaY.getText();
                            double ariaYDouble = Integer.parseInt(holder.toString());
                            holder = ariaX.getText();
                            double ariaXDouble = Integer.parseInt(holder.toString());

                            aria = ariaYDouble * ariaXDouble;

                            holder = cableLenght.getText();
                            cable = Integer.parseInt(holder.toString());
                            responseCc.setText("" + (aria / cable));
                        }
                    }
                }
            }
            else{
                holder = ariaTotal.getText();
                aria = Integer.parseInt(holder.toString());

                if (TextUtils.isEmpty(cableLenght.getText())){
                    cableLenghtTextInputLayout.setError(getString(R.string.cable_missing_error));
                    responseCc.setText("-----");
                }
                else{
                    holder = cableLenght.getText();
                    cable = Integer.parseInt(holder.toString());
                    System.out.println("variables " + aria + " " + cable);
                    responseCc.setText("" + (aria / cable));
                }

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