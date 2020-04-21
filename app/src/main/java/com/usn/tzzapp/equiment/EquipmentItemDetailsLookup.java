package com.usn.tzzapp.equiment;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class EquipmentItemDetailsLookup extends ItemDetailsLookup<Long> {
    RecyclerView mRecView;

    EquipmentItemDetailsLookup(RecyclerView recyclerView){
        this.mRecView = recyclerView;
    }

    /**
     * This will from the X and Y find out what item the user is trying to select
     * @param e the motion event that will let the findChildViewUnder method
     *          find out what item to select
     * @return the details of the selected item
     */
    @Nullable
    @Override
    public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
        View view = mRecView.findChildViewUnder(e.getX(), e.getY());

        if(view != null){
            RecyclerView.ViewHolder viewHolder = mRecView.getChildViewHolder(view);
            if(viewHolder instanceof EquipmentAdapter.EquipmentViewHolder){
                return ((EquipmentAdapter.EquipmentViewHolder) viewHolder).getEquipmentItemDetails(e);
            }
        }
        return null;
    }
}
