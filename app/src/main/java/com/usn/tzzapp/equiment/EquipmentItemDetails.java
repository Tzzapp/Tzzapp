package com.usn.tzzapp.equiment;

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

    @Override
    public int getPosition() {
        return pos;
    }

    @Nullable
    @Override
    public Long getSelectionKey() {
        return identifier;
    }

    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {
        return false;
    }


    @Override
    public boolean inDragRegion(@NonNull MotionEvent e) {
        return true;
    }
}
