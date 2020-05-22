package com.usn.tzzapp.equipment;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

public class EquipmentItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    int pos;
    long identifier;
    
    /*
    * The data type of the identifier variable has to be a primitive long and not a object
    */

    /**
     * @return position of the item that is selected, much like
     * the #getAdapterPosition or #getLayoutPosition method in the standard adapter class
     */
    @Override
    public int getPosition() {
        return pos;
    }

    /**
     * @return identifier of the item that is selected
     */
    @Nullable
    @Override
    public Long getSelectionKey() {
        return identifier;
    }

    /**
     * This controls if the user has to long press or just click to start selecting.
     *
     * When the user has started selection, it only needs a press and
     * not a long click to select more items.
     */
    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {
        return false;
    }


    /**
     * This controls if the user can drag the selection to expand it or not.
     */
    @Override
    public boolean inDragRegion(@NonNull MotionEvent e) {
        return true;
    }
}
