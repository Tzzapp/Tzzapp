package com.usn.tzzapp.equiment;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentItemKeyProvider extends ItemKeyProvider<Long> {
    private Map<Long, Integer> mKeyToPos;
    private List<EquipmentItem> mEquipmentItemList;
    private RecyclerView recyclerView;


    /**
     * Creates a new provider with the given scope.
     *
     * @param equipmentItemList can't be changed at runtime.
     */
    protected EquipmentItemKeyProvider(List<EquipmentItem> equipmentItemList, RecyclerView recyclerView) {
        super(SCOPE_CACHED);
        this.recyclerView = recyclerView;
        mEquipmentItemList = equipmentItemList;


        mKeyToPos = new HashMap<>();
        int i = 0;
        for (EquipmentItem equipmentItem : equipmentItemList) {
            mKeyToPos.put(equipmentItem.id, i);
            i++;
        }

    }


    @Override
    public Long getKey(int position) {

        return mEquipmentItemList.get(position).id;
    }

    /**
     * This will check if the key is linked to a position in the HashMap
     * if not it will be added to it.
     *
     * Only works on API 24 and upwards (the putIfAbsent method)
     * So it will probably crash on lower API levels
     *
     * @param key
     * @return the position that is connected to the key in the @mKeyToPos HashMap
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int getPosition(@NonNull Long key) {
        /* RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForItemId(key);
        return viewHolder == null ? RecyclerView.NO_POSITION : viewHolder.getLayoutPosition();*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mKeyToPos.putIfAbsent(key, mKeyToPos.size());
        }
        return mKeyToPos.get(key)  ;
    }
 }

