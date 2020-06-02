package com.usn.tzzapp.equipment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.usn.tzzapp.equipment.EquipmentItem;
import com.usn.tzzapp.R;

public class PriceHelper extends AppCompatActivity {

    private TableLayout tableLayout;

    private EquipmentViewModel equipmentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_helper);
        initView();

        equipmentViewModel  = new ViewModelProvider(this).get(EquipmentViewModel.class);

        equipmentViewModel.getAllEquipment().observe(this, equipmentItemList -> {

            fillData(equipmentItemList);
        });

        createColumns();


    }

    private void initView() {
        tableLayout = (TableLayout) findViewById(R.id.tableLayoutProduct);
    }


    private void createColumns() {
        TableRow tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Id Column
        TextView textViewId = new TextView(this);
        textViewId.setText(R.string.prod_id_short);
        textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewId.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewId);

        // Name Column
        TextView textViewName = new TextView(this);
        textViewName.setText(R.string.prod_name);
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Price Column
        TextView textViewPrice = new TextView(this);
        textViewPrice.setText(R.string.price_item);
        textViewPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewPrice.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewPrice);


        // Price Column
        TextView textViewCount = new TextView(this);
        textViewCount.setText(R.string.count);
        textViewCount.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewCount.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewCount);

        // Price Column
        TextView textViewTotalPrice = new TextView(this);
        textViewTotalPrice.setText(R.string.total_price);
        textViewTotalPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewTotalPrice.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewTotalPrice);

        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Add Divider
        tableRow = new TableRow(this);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        // Id Column
        textViewId = new TextView(this);
        textViewId.setText("-----------");
        textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewId.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewId);

        // Name Column
        textViewName = new TextView(this);
        textViewName.setText("-----------------");
        textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewName.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewName);

        // Price Column
        textViewPrice = new TextView(this);
        textViewPrice.setText("-----------");
        textViewPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewPrice.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewPrice);

        // Count Column
        textViewCount = new TextView(this);
        textViewCount.setText("-----------");
        textViewCount.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewCount.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewCount);

        // Total cost Column
        textViewCount = new TextView(this);
        textViewCount.setText("-----------");
        textViewCount.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        textViewCount.setPadding(5, 5, 5, 0);
        tableRow.addView(textViewCount);


        tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

    }

    private void fillData(List<EquipmentItem> products) {
        for (EquipmentItem product : products) {
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));


            // Id Column
            TextView textViewId = new TextView(this);
            textViewId.setText(String.valueOf(product.getProd_id()));
            textViewId.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewId.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewId);

            // Name Column
            TextView textViewName = new TextView(this);
            textViewName.setText(product.getName());
            textViewName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewName.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewName);

            // Price Column
            TextView textViewPrice = new TextView(this);
            textViewPrice.setText(String.valueOf(product.getPrice()));
            textViewPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewPrice.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewPrice);

            // Count Column
            TextView textViewCount = new TextView(this);
            textViewCount.setText( String.valueOf(product.getItemCount()));
            textViewCount.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewCount.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewCount);

            // Total Price Column
            TextView textViewTotalPrice = new TextView(this);
            textViewTotalPrice.setText( String.valueOf(product.getTotalPrice()));
            textViewTotalPrice.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            textViewTotalPrice.setPadding(5, 5, 5, 0);
            tableRow.addView(textViewTotalPrice);



            tableLayout.addView(tableRow, new TableLayout.LayoutParams(
                    TableRow.LayoutParams.FILL_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
        }

    }


}