package com.usn.tzzapp.equiment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.SelectionTracker.Builder;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;


import com.google.gson.Gson;
import com.usn.tzzapp.R;
import com.usn.tzzapp.databinding.ActivityEquipmentBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Equipment extends AppCompatActivity /*implements EquipmentAdapter.OnEquipmentListener*/ {

    RecyclerView recyclerView;

    SelectionTracker selectionTracker;

    SharedPreferences sharedPreferences ;
    Set<String> itemsList = new HashSet<>();
    Gson gson = new Gson();

    private List<EquipmentItem> list = new ArrayList<>();

    //private EquipmentAdapter equipmentAdapter = new EquipmentAdapter(list, this);
    private EquipmentAdapter equipmentAdapter = new EquipmentAdapter(list);

    private EquipmentViewModel equipmentViewModel;

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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        equipmentViewModel = new ViewModelProvider(this).get(EquipmentViewModel.class);

        equipmentViewModel.getAllEquipment().observe(this, equipmentItemList -> {
            // Update the cached copy of the words in the adapter.
            equipmentAdapter.submitList(equipmentItemList);
        });


        /*
        itemsList = sharedPreferences.getStringSet("list", itemsList);

        for (String t : itemsList){
           list.add(gson.fromJson(t, EquipmentItem.class));
        }

        for (int i = 0; i < 25; i++){
          //  list.add(new EquipmentItem("Item" , list.size()+1));

        }
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
             * @param key
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
                    Log.d("id", equipmentAdapter.getItemId(item.getPosition())+"");
                    intent.putExtra("id", String.valueOf(equipmentAdapter.getItemId(item.getPosition())));
                    startActivity(intent);

                    return true;
                })
                .build();

      /* boolean hasSelection = sharedPreferences.getBoolean("hasSelection", false);

       if (hasSelection ){
            for (EquipmentItem item : equipmentAdapter.getEquipmentItemList()){
                if (item.isSelected()){
                 selectionTracker.select(item.getId());
                }
            }
        }*/
        //equipmentAdapter.setmSelectionTracker(selectionTracker);

        binding.imageButtonNew.setOnClickListener((v -> {

            //recyclerView.smoothScrollToPosition(0);
            //list.add(new EquipmentItem("Item", list.size()+1));
            //Log.d("list", "" + list.size());

            equipmentViewModel.insert(new EquipmentItem("Item", equipmentAdapter.getItemCount()+1));

            //equipmentAdapter.notifyDataSetChanged();
            equipmentAdapter.notifyItemInserted(equipmentAdapter.getEquipmentItemList().size()+1);
            recyclerView.smoothScrollToPosition(equipmentAdapter.getEquipmentItemList().size()+1);


        }));

        binding.imageButtonDelete.setOnClickListener(v -> {

            if (equipmentAdapter.getEquipmentItemList().size() != 0) {

                recyclerView.post(() -> {
                    for (Iterator<EquipmentItem> iterator = equipmentAdapter.getEquipmentItemList().iterator(); iterator.hasNext(); ) {
                        EquipmentItem equipmentItem = iterator.next();
                        if (selectionTracker.isSelected(equipmentItem.getId())) {
                            //iterator.remove();
                            equipmentViewModel.delete(equipmentItem);
                            equipmentAdapter.notifyItemRemoved(((int) equipmentItem.getId()));
                            //equipmentAdapter.notifyItemRangeChanged((int) equipmentItem.getId(),list.size());


                        }
                    }
                    // equipmentAdapter.submitList(list);
                   // selectionTracker.clearSelection();

                });

               /* equipmentAdapter.notifyItemRemoved(equipmentItem.id);
                  equipmentAdapter.notifyItemRangeChanged(equipmentItem.id,list.size());
                  equipmentAdapter.notifyDataSetChanged();
                */
            }

        });

        observer();

    }

    @Override
    protected void onPause() {
        super.onPause();
     /*   itemsList.clear();
        for (EquipmentItem equipmentItem : list) {
          itemsList.add(gson.toJson(equipmentItem));
        }*/
        sharedPreferences.edit().putBoolean("hasSelection", selectionTracker.hasSelection()).apply();
       // sharedPreferences.edit().putStringSet("list", itemsList).apply();
    }

    /**
     * This method will start when the user opens the equipment activity,
     * here it will listen for a long press on a item in the equipment adapter.
     *
     * Then it will mark that item as selected using @equipmentItem.setSelected(true)
     * and change its properties as set in the "item_color" xml file in res/color
     *
     * This makes use of the selection tracker and its OnItemStateChanged observer
     * and the @itemView.setActivated(item.isSelected()) in equipment adapter class,
     *
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

                for (Iterator<EquipmentItem> iterator = equipmentAdapter.getEquipmentItemList().iterator(); iterator.hasNext(); ) {
                    EquipmentItem equipmentItem = iterator.next();

                    equipmentItem.setSelected(false);
                    if (selectionTracker.isSelected(equipmentItem.getId())){
                        equipmentItem.setSelected(true);
                    }
                }
            }

        });
        //selectionTracker.clearSelection();
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

  /*  @Override
    public void onEquipmentClick(int pos) {
        //Log.d("CLicked", "" + list.get(pos).getProd_id() + " Selected : " +selectionTracker.getSelection().toString());
       // equipmentAdapter.notifyDataSetChanged();
        //list.get(pos).getName();
    }*/
}
