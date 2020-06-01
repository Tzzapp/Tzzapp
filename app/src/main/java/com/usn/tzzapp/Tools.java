package com.usn.tzzapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        setTitle(R.string.tools);


        Button buttonLamps = findViewById(R.id.buttonLamps);
        Button buttonHeating = findViewById(R.id.buttonHeating);

        final Intent intentForms1 = new Intent(this, Lamps.class);
        final Intent intentForms2 = new Intent(this, Heating.class);

        buttonLamps.setOnClickListener(v -> startActivity(intentForms1));
        buttonHeating.setOnClickListener(v -> startActivity(intentForms2));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
