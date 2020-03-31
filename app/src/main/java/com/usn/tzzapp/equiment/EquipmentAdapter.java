package com.usn.tzzapp.equiment;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.tzzapp.databinding.EquipmentItemBinding;

import java.util.List;

/**
 * This adapter will allow, the list to be shown to the user in the recycler view.
 *
 * It will also allow the list to be customised to fit our needs.
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    private List<EquipmentItem> equipmentItemList;
    //private OnEquipmentListener onEquipmentListener;
    private SelectionTracker mSelectionTracker;

  /*  public EquipmentAdapter(List<EquipmentItem> list, OnEquipmentListener onEquipmentListener) {

        this.equipmentItemList = list;
        //this.onEquipmentListener = onEquipmentListener;


        *//* setHasStableIds(true);
         * Note to self and others, do not use this, it will make the list screwed up,
         * it caps it at 9, and will crash the app
         *
         *//*
    }*/
    public EquipmentAdapter(List<EquipmentItem> list){
        this.equipmentItemList = list;
    }

    public void setEquipmentItemList(List<EquipmentItem> equipmentItemList) {
        this.equipmentItemList = equipmentItemList;
        notifyDataSetChanged();
    }

    public void setmSelectionTracker (SelectionTracker<Long> selectionTracker){
        this.mSelectionTracker = selectionTracker;
    }

    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        EquipmentItemBinding itemBinding = EquipmentItemBinding.inflate(layoutInflater, parent, false);

        //return new EquipmentViewHolder(itemBinding, onEquipmentListener);
        return new EquipmentViewHolder(itemBinding);
    }


    /**
     * @param holder
     * @param position
     *
     * This will using the @position find out where to place the items from the list on the users screen
     *
     * It also using the @mSelectionTracker find out what items are selected and what items are not.
     *
     * Before the @holder can bind them to the screen
     */
    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        EquipmentItem equipmentItem = equipmentItemList.get(position);

        boolean isSelected = false;
        if (mSelectionTracker != null){
            if(mSelectionTracker.isSelected(equipmentItem.id)){
                isSelected = true;

                Log.d("Selected", equipmentItem.id + " Selected : " + mSelectionTracker.getSelection() );

            }
        }


        holder.bind(equipmentItem, position, isSelected );
    }


    /**
     * This gives the list the number of what item to put where on the screen.
     * */
    @Override
    public int getItemCount() {
        return equipmentItemList.size();
    }


    /**
     * This interface is used to find out what is clicked on the screen/list
     * And what position it has .
     */
  /*  public interface OnEquipmentListener {
        void onEquipmentClick(int pos);
    }*/


    class EquipmentViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

        //OnEquipmentListener onEquipmentListener;
        private EquipmentItemBinding binding;
        private EquipmentItemDetails equipmentItemDetails;


      /*  public EquipmentViewHolder(EquipmentItemBinding binding, OnEquipmentListener onEquipmentListener) {
            super(binding.getRoot());
            this.binding = binding;
            //this.onEquipmentListener = onEquipmentListener;
            //itemView.setOnClickListener(this);

            equipmentItemDetails = new EquipmentItemDetails();
        }*/

        public EquipmentViewHolder(EquipmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            equipmentItemDetails = new EquipmentItemDetails();
        }

        /**
         * Gets the items from the list (Here : equipmentItemList ),
         * that was given when the adapter was created,
         * and pushes the requested values out to the screen.
         *
         * These values/strings can be set in the item  xml file (this case equipment_item.xml)
         * But they also listen to what the value of the object in that position
         * from the list it was given during creation of the adapter
         *
         * This will also show the result of the selection tracker using
         * @itemView.setActivated(isSelected)
         *
         * This will then, send the value to the ColorState in res/color/item_color.xml
         */
        public void bind(EquipmentItem item, int pos, boolean isSelected) {

            /*
            *  Without these, the selection will only work for the first item in the list
            *  and it crash allow if the user starts the selection
            *  on any other item than the first.
             */
            equipmentItemDetails.pos = pos;
            equipmentItemDetails.identifier = item.id;

            binding.setEquipmentItem(item);
            itemView.setActivated(isSelected);
            binding.executePendingBindings();


        }

        public ItemDetailsLookup.ItemDetails<Long> getEquipmentItemDetails(MotionEvent motionEvent){
            return equipmentItemDetails;
        }

        /**
         * This will allow the view holder to find out which item was clicked.
         * To do this it uses the OnClickListener from Android.
         *
         * To be used it has to be implemented in the class,
         * where the recycler view is used.
         * @param v
         *
         * For it to work, itemView.setOnClickListener(this) has to be set in the View Holder constructor
         * Item view does not have to be declared, it is declared in Equipment Adapters super class (RecyclerView.java)
         *
         * This is not in use currently but might be useful later on
         * if we want to be able to click on items and have a window come up
         */
      /*  @Override
        public void onClick(View v) {
            *//*onEquipmentListener.onEquipmentClick(getAdapterPosition());*//*
        }*/
    }
}
