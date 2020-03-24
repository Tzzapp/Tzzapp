package com.usn.tzzapp.equiment;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

public class EquipmentItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
    int pos;
    Long identifier;

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
