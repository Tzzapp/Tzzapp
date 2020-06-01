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
    EditText cableLength;
    EditText responseCc; //cc is the professional term for the distance between different sections of the cable as it bends

    TextInputLayout areaXTextInputLayout;
    TextInputLayout areaYTextInputLayout;
    TextInputLayout areaTotalTextInputLayout;
    TextInputLayout cableLengthTextInputLayout;

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
        cableLength = findViewById(R.id.editTextCableLength);
        responseCc = findViewById(R.id.editTextCc);
        responseCc.setKeyListener(null);

        areaXTextInputLayout = findViewById(R.id.textfieldAreaX);
        areaYTextInputLayout = findViewById(R.id.textfieldAreaY);
        areaTotalTextInputLayout = findViewById(R.id.textfieldAreaTotal);
        cableLengthTextInputLayout = findViewById(R.id.textfieldCableLength);


        MathMaster = findViewById(R.id.buttonCalculate);



        MathMaster.setOnClickListener(v -> {
            double cable;
            double area;

            cableLengthTextInputLayout.setErrorEnabled(false);
            areaTotalTextInputLayout.setErrorEnabled(false);
            areaYTextInputLayout.setErrorEnabled(false);
            areaXTextInputLayout.setErrorEnabled(false);

            // ||

            if (TextUtils.isEmpty(areaTotal.getText())){

                if (TextUtils.isEmpty(areaX.getText())){

                    if (TextUtils.isEmpty(areaY.getText())){
                        areaTotalTextInputLayout.setError(getString(R.string.area_missing_error));
                        responseCc.setText("-----");
                        if (TextUtils.isEmpty(cableLength.getText())){
                            cableLengthTextInputLayout.setError(getString(R.string.cable_missing_error));
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
                        if (TextUtils.isEmpty(cableLength.getText())){
                            cableLengthTextInputLayout.setError(getString(R.string.cable_missing_error));
                            responseCc.setText("-----");
                        }
                        else{
                            holder = areaY.getText();
                            double ariaYDouble = Integer.parseInt(holder.toString());
                            holder = areaX.getText();
                            double ariaXDouble = Integer.parseInt(holder.toString());

                            area = ariaYDouble * ariaXDouble;

                            holder = cableLength.getText();
                            cable = Integer.parseInt(holder.toString());
                            responseCc.setText("" + (area / cable));
                        }
                    }
                }
            }
            else{
                holder = areaTotal.getText();
                area = Integer.parseInt(holder.toString());

                if (TextUtils.isEmpty(cableLength.getText())){
                    cableLengthTextInputLayout.setError(getString(R.string.cable_missing_error));
                    responseCc.setText("-----");
                }
                else{
                    holder = cableLength.getText();
                    cable = Integer.parseInt(holder.toString());
                    System.out.println("variables " + area + " " + cable);
                    responseCc.setText("" + (area / cable));
                }

            }

        });
    }
}