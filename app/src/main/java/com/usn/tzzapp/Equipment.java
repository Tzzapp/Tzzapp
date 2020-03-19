package com.usn.tzzapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;


import com.usn.tzzapp.databinding.ActivityEquipmentBinding;

import java.util.ArrayList;
import java.util.List;

public class Equipment extends AppCompatActivity implements EquipmentAdapter.OnEquimentListener {

    RecyclerView recyclerView;

    private List<EquipmentItem> list = new ArrayList<>();

    private EquipmentAdapter equipmentAdapter = new EquipmentAdapter(list, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /**
         * This code block replaces the standard setContentView with the one from DataBindingUtil.
         * This makes it able to use android data binding, and makes it take objects,
         * instead of setting the values manually here.
         */
        ActivityEquipmentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment);

        binding.executePendingBindings();
        // https://stackoverflow.com/questions/53043412/android-why-use-executependingbindings-in-recyclerview
        // 06.03.2020


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);



        for (int i = 0; i < 25; i++){
            list.add(new EquipmentItem("Item" , list.size()+1));
        }


        // Connects to the recycler view in the layout file
        recyclerView = binding.recview;

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(equipmentAdapter);
        recyclerView.setHasFixedSize(true);



        binding.imageButtonNew.setOnClickListener((v -> {

            recyclerView.smoothScrollToPosition(0);
            list.add(new EquipmentItem("Item" , list.size()+1));
            Log.d("list", "" + list.size());

            //equipmentAdapter.notifyDataSetChanged();
            equipmentAdapter.notifyItemInserted(list.size()+1);
            recyclerView.smoothScrollToPosition(list.size()+1);



        }));







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    /**
     * This will let you use value in the int variable "pos" to find the right item in the list
     * So that when you got this item selected, the requested action will be done on the right item.
     * @param pos
     */

    @Override
    public void onEquipmentClick(int pos) {
        Log.d("CLicked", "" + list.get(pos).getProd_id());


        //list.get(pos).getName();
    }
}
