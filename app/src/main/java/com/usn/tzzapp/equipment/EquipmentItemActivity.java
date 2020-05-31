package com.usn.tzzapp.equipment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.TextKeyListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import com.usn.tzzapp.R;
import com.usn.tzzapp.databinding.ActivityEquipmentItemBinding;

import static android.text.TextUtils.isEmpty;
import static android.text.method.TextKeyListener.*;


public class EquipmentItemActivity extends AppCompatActivity {

    public static final int MAX_VALUE = 10000;
    public static final int MIN_VALUE = 0;

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

        binding.imageView.setOnClickListener(v -> {
            if(editing) {
                ImagePicker.create(this) // Activity or Fragment
                        .single()
                        .folderMode(true)
                        .start();
            }
        else {
                Toast.makeText(this, R.string.select_picture_notice, Toast.LENGTH_LONG).show();
            }
        });

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
        binding.numberPickerItem.setMinValue(MIN_VALUE);
        binding.numberPickerItem.setMaxValue(MAX_VALUE);

        binding.fabEdit.setOnClickListener((view) -> {

            if (editing) {
                if(!isEmpty(binding.editTextName.getText())){
                    equipmentItem.setName(binding.editTextName.getText().toString());
                }
                if(!isEmpty(binding.editTextProdId.getText())){
                    equipmentItem.setProd_id(Long.parseLong(String.valueOf(binding.editTextProdId.getText())));
                }

                if(!isEmpty(binding.editTextDescription.getText())){
                    equipmentItem.setItemDescription(binding.editTextDescription.getText().toString());
                }

                equipmentItem.setItemCount(binding.numberPickerItem.getValue());

                viewModel.update(binding.getEquipmentItem());

                disableEditors(binding);

                binding.fabEdit.setText(R.string.edit_button_label);
                editing = false;

            } else {
                binding.numberPickerItem.setEnabled(true);
                binding.editTextName.setKeyListener(text);
                binding.editTextProdId.setKeyListener(text);
                binding.editTextDescription.setKeyListener(text);
                binding.editTextProdId.setInputType(InputType.TYPE_CLASS_NUMBER);
                binding.fabEdit.setText(R.string.save);
                editing = true;
            }
        });


    }

    private void disableEditors(ActivityEquipmentItemBinding binding){
        binding.editTextName.setKeyListener(null);
        binding.editTextProdId.setKeyListener(null);
        binding.editTextDescription.setKeyListener(null);
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
    @Override
    public void onActivityResult(int requestCode, final int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
            equipmentItem.setImageSrc(image.getPath());
            viewModel.update(equipmentItem);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
