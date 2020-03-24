package com.usn.tzzapp.equiment;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class EquipmentItemDetailsLookup extends ItemDetailsLookup<String> {
    RecyclerView mRecView;

    EquipmentItemDetailsLookup(RecyclerView recyclerView){
        this.mRecView = recyclerView;
    }

    @Nullable
    @Override
    public ItemDetails<String> getItemDetails(@NonNull MotionEvent e) {
        View view = mRecView.findChildViewUnder(e.getX(), e.getY());

        if(view != null){
            RecyclerView.ViewHolder viewHolder = mRecView.getChildViewHolder(view);
            if(viewHolder instanceof EquipmentAdapter.EquipmentViewHolder){
                return ((EquipmentAdapter.EquipmentViewHolder) viewHolder).getEquimentItemDetails(e);
            }
        }
        return null;
    }
}
