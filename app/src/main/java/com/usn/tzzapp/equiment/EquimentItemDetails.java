package com.usn.tzzapp.equiment;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;

public class EquimentItemDetails extends ItemDetailsLookup.ItemDetails<String> {
    int pos;
    String identifier;

    @Override
    public int getPosition() {
        return pos;
    }

    @Nullable
    @Override
    public String getSelectionKey() {
        return identifier;
    }

    @Override
    public boolean inSelectionHotspot(@NonNull MotionEvent e) {
        return true;
    }


    @Override
    public boolean inDragRegion(@NonNull MotionEvent e) {
        return true;
    }
}
