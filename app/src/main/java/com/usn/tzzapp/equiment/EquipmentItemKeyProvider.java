package com.usn.tzzapp.equiment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentItemKeyProvider extends ItemKeyProvider<String> {
    private Map<String, Integer> mKeyToPos;
    private List<EquipmentItem> mEquipmentItemList;


    /**
     * Creates a new provider with the given scope.
     *
     * @param equipmentItemList can't be changed at runtime.
     */
    protected EquipmentItemKeyProvider(List<EquipmentItem> equipmentItemList) {
        super(SCOPE_MAPPED);
        mEquipmentItemList = equipmentItemList;

        mKeyToPos = new HashMap<>(mEquipmentItemList.size());
        int i = 0;
        for (EquipmentItem equipmentItem : equipmentItemList){
            mKeyToPos.put(equipmentItem.id, i);
            i++;
        }

    }


    @Override
    public @Nullable String getKey(int position) {
        return mEquipmentItemList.get(position).id;
    }

    @Override
    public int getPosition(@NonNull String key) {
        if (mKeyToPos.containsKey(key)){
            return mKeyToPos.get(key);
        }
        return RecyclerView.NO_POSITION ;

    }
}
