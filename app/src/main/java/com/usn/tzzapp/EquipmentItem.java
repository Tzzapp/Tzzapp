package com.usn.tzzapp;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * This class extends @BaseObservable
 * The reason it does that it so that the values can be observed
 * and pushed out to a recycler view or any other from of text view
 */
public class EquipmentItem extends BaseObservable {

    private String name;
    private int prod_id;

    public EquipmentItem(String name, int prod_id){
        this.name = name;
        this.prod_id = prod_id;
    }

    public EquipmentItem(String name){
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }


    @Bindable
    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
}
