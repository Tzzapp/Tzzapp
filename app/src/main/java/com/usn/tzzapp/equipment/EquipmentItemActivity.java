package com.usn.tzzapp.equipment;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.TextKeyListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.usn.tzzapp.R;
import com.usn.tzzapp.databinding.ActivityEquipmentItemBinding;

import static android.text.TextUtils.isEmpty;
import static android.text.method.TextKeyListener.*;


public class EquipmentItemActivity extends AppCompatActivity {

    EquipmentViewModel viewModel;

    EquipmentItem equipmentItem;

    boolean editing = false;

    TextKeyListener text = new TextKeyListener(Capitalize.SENTENCES, false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityEquipmentItemBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment_item);

        Toolbar toolbar = findViewById(R.id.itemToolbar);
        setSupportActionBar(toolbar);

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
        disableEditors(binding);

       /* binding.editTextName.setOnClickListener((v) -> {
            equipmentItem.setName(binding.editTextName.getText().toString());
            viewModel.update(equipmentItem);

        });*/

       /* binding.editTextProdId.setOnClickListener((v) -> {
            if(!isEmpty(binding.editTextProdId.getText())){
                equipmentItem.setProd_id(Integer.parseInt(String.valueOf(binding.editTextProdId.getText())));
                viewModel.update(equipmentItem);
            }
        });*/

        binding.fabEdit.setOnClickListener((view) -> {

            if (editing) {
                if(!isEmpty(binding.editTextName.getText())){
                    equipmentItem.setName(binding.editTextName.getText().toString());
                }
                if(!isEmpty(binding.editTextProdId.getText())){
                    equipmentItem.setProd_id(Long.parseLong(String.valueOf(binding.editTextProdId.getText())));
                }
                viewModel.update(equipmentItem);

                disableEditors(binding);
                binding.fabEdit.setText(R.string.edit_button_label);
                editing = false;

            } else {
                binding.editTextName.setKeyListener(text);
                binding.editTextProdId.setKeyListener(text);
                binding.editTextProdId.setInputType(InputType.TYPE_CLASS_NUMBER);
                binding.fabEdit.setText(R.string.save);
                editing = true;
            }
        });


    }

    private void disableEditors(ActivityEquipmentItemBinding binding){
        binding.editTextName.setKeyListener(null);
        binding.editTextProdId.setKeyListener(null);
        binding.numberPickerItem.setEnabled(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.close_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.close){
            finish();
        }

        return  true;
    }
}
