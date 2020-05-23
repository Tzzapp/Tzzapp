package com.usn.tzzapp.equipment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.usn.tzzapp.BR;

/**
 * This class extends @BaseObservable
 * The reason it does that it so that the values can be observed
 * and pushed out to a recycler view or any other from of text view
 */
@Entity(tableName = "equipment_table")
public class EquipmentItem extends BaseObservable implements Comparable<EquipmentItem> {

    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private long prod_id;

    @ColumnInfo(name = "selected")
    private boolean selected;

    @ColumnInfo(name = "item_count")
    private int itemCount;

    @Ignore
    public EquipmentItem(String name, int prod_id){
        this.name = name;
        this.prod_id = prod_id;
        //id = ""+prod_id;
        id = hashCode();
    }

    public EquipmentItem(String name){
        this.name = name;
        //id = ""+prod_id;
        id = hashCode();
    }

    @Ignore
    public EquipmentItem(int id){
        this.name = "Item";
        this.id = id;
    }

    /**
     * This method makes it easier to find what item that is selected
     * and does it without needing to use a handler/post and notifyItemChanged(); in the adapter class for equipment,
     * so that the animations wont run crazy and make the list a rave party
     * @param selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        notifyPropertyChanged(BR.selected);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (other instanceof EquipmentItem) {
            EquipmentItem equipmentItem = (EquipmentItem) other;
            return name.equals(equipmentItem.getName())
                    && (prod_id == ((EquipmentItem) other).getProd_id())
                    && (itemCount == ((EquipmentItem) other).getItemCount());
        } else {
            return false;
        }
    }

    @Bindable
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public long getProd_id() {
        return prod_id;
    }

    public void setProd_id(long prod_id) {
        this.prod_id = prod_id;
        notifyPropertyChanged(BR.prod_id);
    }

    @Bindable
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
        notifyPropertyChanged(BR.itemCount);
    }

    @Override
    public int compareTo(EquipmentItem o) {
        return Long.compare(this.prod_id, o.prod_id);
    }
}
