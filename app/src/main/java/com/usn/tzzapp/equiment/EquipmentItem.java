package com.usn.tzzapp.equiment;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * This class extends @BaseObservable
 * The reason it does that it so that the values can be observed
 * and pushed out to a recycler view or any other from of text view
 */
public class EquipmentItem extends BaseObservable {

    private String name;
    private int id;
    private int prod_id;

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
    }

    @Bindable
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Bindable
    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
}
