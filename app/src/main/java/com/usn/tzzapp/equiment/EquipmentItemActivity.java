package com.usn.tzzapp.equiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.usn.tzzapp.R;
import com.usn.tzzapp.databinding.ActivityEquipmentItemBinding;



public class EquipmentItemActivity extends AppCompatActivity {

    EquipmentViewModel viewModel;

    EquipmentItem equipmentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityEquipmentItemBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_item);

         viewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);

        Bundle extras = getIntent().getExtras();
        String userName ="";

        if (extras != null) {
            userName = extras.getString("id");
        }

        viewModel.getEquipmentItem(userName).observe(this, equipmentItem -> {
            binding.setEquipmentItem(equipmentItem);
            this.equipmentItem = equipmentItem;
            setTitle(equipmentItem.getName());

        });

        binding.okButton.setOnClickListener(v -> {
            viewModel.update(equipmentItem);
            finish();

        });

        binding.cancelButton.setOnClickListener(v -> {
           finish();

        });
        
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
