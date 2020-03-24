package com.usn.tzzapp.equiment;

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
    private OnEquimentListener onEquimentListener;
    private SelectionTracker<String> mSelectionTracker;

    public EquipmentAdapter(List<EquipmentItem> list, OnEquimentListener onEquimentListener) {

        this.equipmentItemList = list;
        this.onEquimentListener = onEquimentListener;
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

        return new EquipmentViewHolder(itemBinding, onEquimentListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        EquipmentItem equipmentItem = equipmentItemList.get(position);

        boolean isSelected = false;
        if (mSelectionTracker != null){
            if(mSelectionTracker.isSelected(equipmentItem.getId())){
                isSelected = true;
                Log.d("Selected", equipmentItem.getId() + " Selected ; " + mSelectionTracker.getSelection().toString() );

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
    public interface OnEquimentListener{
        void onEquipmentClick(int pos);
    }


    class EquipmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnEquimentListener onEquimentListener;
        private EquipmentItemBinding binding;
        private final EquimentItemDetails equimentItemDetails;


        public EquipmentViewHolder(EquipmentItemBinding binding, OnEquimentListener onEquimentListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onEquimentListener = onEquimentListener;
            itemView.setOnClickListener(this);

            equimentItemDetails = new EquimentItemDetails();
        }

        /**
         * Gets the items from the list (Here : equipmentItemList ),
         * that was given when the adapter was created,
         * and pushes the requested values out to the screen.
         *
         * These values/strings can be set in the item  xml file (this case equipment_item.xml)
         * But they also listen to what the value of the object in that position
         * from the list it was given during creation of the adapter
         */
        public void bind(EquipmentItem item, int pos, boolean isSelected) {
            equimentItemDetails.pos = pos;
            equimentItemDetails.identifier = item.getId();

            binding.setEquipmentItem(item);
            binding.executePendingBindings();
            itemView.setActivated(isSelected);
        }

        public ItemDetailsLookup.ItemDetails<Long> getEquimentItemDetails(MotionEvent motionEvent){
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
         */
        @Override
        public void onClick(View v) {
            onEquimentListener.onEquipmentClick(getAdapterPosition());
        }
    }
}
