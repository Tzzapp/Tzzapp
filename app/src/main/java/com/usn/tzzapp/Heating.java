package com.usn.tzzapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class Heating extends AppCompatActivity {

    EditText areaX;
    EditText areaY;
    EditText areaTotal;
    EditText cableLenght;
    EditText responseCc; //cc is the profecjonal term for the distance betwen difrent sections of the cable as it bends

    TextInputLayout areaXTextInputLayout;
    TextInputLayout areaYTextInputLayout;
    TextInputLayout areaTotalTextInputLayout;
    TextInputLayout cableLenghtTextInputLayout;

    Button MathMaster;
    Editable holder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heating);

        setTitle(R.string.heating);
        areaX = findViewById(R.id.editTextAreaX);
        areaY = findViewById(R.id.editTextAreaY);
        areaTotal = findViewById(R.id.editTextAriaTotal);
        cableLenght = findViewById(R.id.editTextCableLength);
        responseCc = findViewById(R.id.editTextCc);
        responseCc.setKeyListener(null);

        areaXTextInputLayout = findViewById(R.id.textfieldAreaX);
        areaYTextInputLayout = findViewById(R.id.textfieldAreaY);
        areaTotalTextInputLayout = findViewById(R.id.textfieldAreaTotal);
        cableLenghtTextInputLayout = findViewById(R.id.textfieldCableLength);


        MathMaster = findViewById(R.id.buttonCalculate);



        MathMaster.setOnClickListener(v -> {
            double cable;
            double aria;

            cableLenghtTextInputLayout.setErrorEnabled(false);
            areaTotalTextInputLayout.setErrorEnabled(false);
            areaYTextInputLayout.setErrorEnabled(false);
            areaXTextInputLayout.setErrorEnabled(false);

            // ||

            if (TextUtils.isEmpty(areaTotal.getText())){

                if (TextUtils.isEmpty(areaX.getText())){

                    if (TextUtils.isEmpty(areaY.getText())){
                        areaTotalTextInputLayout.setError(getString(R.string.area_missing_error));
                        responseCc.setText("-----");
                        if (TextUtils.isEmpty(cableLenght.getText())){
                            cableLenghtTextInputLayout.setError(getString(R.string.cable_missing_error));
                            responseCc.setText("-----");
                        }
                    }
                    else{
                        areaXTextInputLayout.setError(getString(R.string.room_x_missing_error));
                        responseCc.setText("-----");
                    }

                }
                else{
                    if (TextUtils.isEmpty(areaY.getText())){
                        areaYTextInputLayout.setError(getString(R.string.room_y_missing_error));
                        responseCc.setText("-----");
                    }
                    else{
                        if (TextUtils.isEmpty(cableLenght.getText())){
                            cableLenghtTextInputLayout.setError(getString(R.string.cable_missing_error));
                            responseCc.setText("-----");
                        }
                        else{
                            holder = areaY.getText();
                            double ariaYDouble = Integer.parseInt(holder.toString());
                            holder = areaX.getText();
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
                holder = areaTotal.getText();
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
}