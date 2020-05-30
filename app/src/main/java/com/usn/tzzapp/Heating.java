package com.usn.tzzapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Heating extends AppCompatActivity {

    EditText ariaX;
    EditText ariaY;
    EditText ariaTotal;
    EditText cableLenght;
    EditText cc; //cc is the profecjonal term for the distance betwen difrent sections of the cable as it bends

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
        cc = findViewById(R.id.editTextCc);


        MathMaster = findViewById(R.id.buttonCalculate);



        MathMaster.setOnClickListener(v -> {

            double aria, cable;

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