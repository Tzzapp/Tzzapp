package com.usn.tzzapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.tzzapp.databinding.EquipmentItemBinding;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    private List<EquipmentItem> equipmentItemList;

    public EquipmentAdapter(List<EquipmentItem> list) {
        this.equipmentItemList = list;

    }

    public void setEquipmentItemList(List<EquipmentItem> equipmentItemList) {
        this.equipmentItemList = equipmentItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        EquipmentItemBinding itemBinding = EquipmentItemBinding.inflate(layoutInflater, parent, false);

        return new EquipmentViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        EquipmentItem equipmentItem = equipmentItemList.get(position);
        holder.bind(equipmentItem);
    }


    /**
     * This gives the list the number of what item to put where on the screen.
     * */
    @Override
    public int getItemCount() {
        return equipmentItemList.size();
    }


    class EquipmentViewHolder extends RecyclerView.ViewHolder {

        private EquipmentItemBinding binding;

        public EquipmentViewHolder(EquipmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

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
        public void bind(EquipmentItem item) {
            binding.setEquipmentItem(item);
            binding.executePendingBindings();
        }

    }
}
