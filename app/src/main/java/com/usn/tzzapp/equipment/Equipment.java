package com.usn.tzzapp.equipment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.SelectionTracker.Builder;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.usn.tzzapp.R;
import com.usn.tzzapp.databinding.ActivityEquipmentBinding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipment extends AppCompatActivity /*implements EquipmentAdapter.OnEquipmentListener*/ implements ActionMode.Callback{

    RecyclerView recyclerView;

    SelectionTracker<Long> selectionTracker;

    private List<EquipmentItem> list = new ArrayList<>();

    private EquipmentAdapter equipmentAdapter = new EquipmentAdapter(list);

    private EquipmentViewModel equipmentViewModel;

    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        /*
         *  This code block replaces the standard setContentView with the one from DataBindingUtil.
         *  This makes it able to use android data binding, and makes it take objects,
         *  instead of setting the values manually here.
         */
        ActivityEquipmentBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_equipment);

        binding.executePendingBindings();
        // https://stackoverflow.com/questions/53043412/android-why-use-executependingbindings-in-recyclerview
        // 06.03.2020

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        setTitle(R.string.equipment);

        equipmentViewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);

        equipmentViewModel.getAllEquipment().observe(this, equipmentItemList -> {
            // Update the cached copy of the items in the adapter.
            equipmentAdapter.submitList(equipmentItemList);
        });

        /*

        equipmentAdapter.submitList(list);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort(EquipmentItem::compareTo);
        }*/

        // Connects to the recycler view in the layout file
        recyclerView = binding.recview;

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(equipmentAdapter);
        recyclerView.setHasFixedSize(true);

        selectionTracker = new Builder<>("equipment-selection",
                recyclerView, new ItemKeyProvider<Long>(ItemKeyProvider.SCOPE_MAPPED) {
            /**
             * @param position This will be given in by the selection tracker/Recycler view
             * @return a long value of what position the current selected item hasSelection
             */
            @Override
            public Long getKey(int position) {
                return equipmentAdapter.getItemId(position);
            }

            /**
             * This can be done easier but by doing it, this way will make
             * sure the that the item is there and that the tracker wont run in
             * to a item that is null and therefore crash from a NullPointerException
             *
             * @param key the long value of the id of the item
             * @return this will return RecyclerView.NO_POSITION or viewHolder.getLayoutPosition(),
             * depending on whether viewHolder is null or not
             */
            @Override
            public int getPosition(@NonNull Long key) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForItemId(key);
                return viewHolder == null ? RecyclerView.NO_POSITION : viewHolder.getLayoutPosition();
            }
        }, new EquipmentItemDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withOnItemActivatedListener((item, e) -> {
                    Intent intent = new Intent(this, EquipmentItemActivity.class);
                    Log.d("id", equipmentAdapter.getItemId(item.getPosition()) + "");
                    intent.putExtra("id", String.valueOf(equipmentAdapter.getItemId(item.getPosition())));
                    startActivity(intent);

                    return true;
                })
                .build();

        binding.fab.setOnClickListener((v -> {

            recyclerView.post(() -> {
                equipmentViewModel.insert(new EquipmentItem("Item", equipmentAdapter.getItemCount() + 1));
            });

            //equipmentAdapter.notifyItemInserted(equipmentAdapter.getEquipmentItemList().size()+1);
            recyclerView.smoothScrollToPosition(equipmentAdapter.getEquipmentItemList().size() + 1);


        }));

        binding.bar.setOnMenuItemClickListener(item -> {

            if (item.getItemId() == R.id.delete) {

                if (equipmentAdapter.getEquipmentItemList().size() != 0) {
                    if (selectionTracker.getSelection().size() >= 1) {
                        MaterialAlertDialogBuilder materialAlertDialogBuilder = new MaterialAlertDialogBuilder(new ContextThemeWrapper(this, R.style.equipmentTheme));
                        materialAlertDialogBuilder.setTitle(getString(R.string.delete_title) + selectionTracker.getSelection().size() + getString(R.string.delete_item_title));
                        materialAlertDialogBuilder.setMessage(R.string.delete_item_string);
                        materialAlertDialogBuilder.setPositiveButton(getString(android.R.string.ok), (dialog, which) -> {

                            recyclerView.post(() -> {
                                for (Iterator<EquipmentItem> iterator = equipmentAdapter.getEquipmentItemList().iterator(); iterator.hasNext(); ) {
                                    EquipmentItem equipmentItem = iterator.next();
                                    if (selectionTracker.isSelected((long) equipmentItem.getId())) {
                                        equipmentViewModel.delete(equipmentItem);
                                        equipmentAdapter.notifyItemRemoved(equipmentItem.getId());

                                    }
                                }
                                selectionTracker.clearSelection();

                            });
                        });
                        materialAlertDialogBuilder.setNegativeButton(R.string.cancel_button, (dialog, which) -> {

                        });
                        materialAlertDialogBuilder.show();

                    } else {
                        Toast.makeText(this, R.string.zero_items, Toast.LENGTH_LONG).show();
                    }
                }
            }

            return false;
        });

        observer();

    }

    /**
     * This method will start when the user opens the equipment activity,
     * here it will listen for a long press on a item in the equipment adapter.
     * <p>
     * Then it will mark that item as selected using @equipmentItem.setSelected(true)
     * and change its properties as set in the "item_color" xml file in res/color
     * <p>
     * This makes use of the selection tracker and its OnItemStateChanged observer
     * and the @itemView.setActivated(item.isSelected()) in equipment adapter class,
     */
    public void observer() {
        selectionTracker.addObserver(new SelectionTracker.SelectionObserver() {

            /**
             * @param key      the object id / hashcode for the item that is selected
             * @param selected a boolean that tells if an item is selected or not
             */
            @Override
            public void onItemStateChanged(@NonNull Object key, boolean selected) {
                super.onItemStateChanged(key, selected);

                for (EquipmentItem equipmentItem : equipmentAdapter.getEquipmentItemList()) {
                    equipmentItem.setSelected(false);
                    if (selected) {
                        equipmentItem.setSelected(true);
                    }
                }
            }


            @Override
            public void onSelectionChanged() {
                if (selectionTracker.getSelection().size() > 0) {
                    if (actionMode == null) {
                        actionMode = startSupportActionMode(Equipment.this);
                    }
                    actionMode.setTitle(String.valueOf(selectionTracker.getSelection().size()));
                } else if (actionMode != null) {
                    actionMode.finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        selectionTracker.clearSelection();
        this.actionMode = null;
    }

    /**
     * This will let you use value in the int variable "pos" to find the right item in the list
     * So that when you got this item selected, the requested action will be done on the right item.
     * @param pos
     */

  /*  @Override
    public void onEquipmentClick(int pos) {
        //Log.d("CLicked", "" + list.get(pos).getProd_id() + " Selected : " +selectionTracker.getSelection().toString());
       // equipmentAdapter.notifyDataSetChanged();
        //list.get(pos).getName();
    }*/
}