package com.usn.tzzapp.equiment;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipmentItemKeyProvider extends ItemKeyProvider<Long> {
    private Map<Long, Integer> mKeyToPos;
    private List<EquipmentItem> mEquipmentItemList;


    /**
     * Creates a new provider with the given scope.
     *
     * @param equipmentItemList can't be changed at runtime.
     */
    protected EquipmentItemKeyProvider(List<EquipmentItem> equipmentItemList, RecyclerView recyclerView) {
        super(SCOPE_CACHED);
        this.recyclerView = recyclerView;
        mEquipmentItemList = equipmentItemList;

        mKeyToPos = new HashMap<>(mEquipmentItemList.size());
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

    @Override
    public int getPosition(@NonNull Long key) {
        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForItemId(key);
        return viewHolder == null ? RecyclerView.NO_POSITION : viewHolder.getLayoutPosition();
    }

    }
}
