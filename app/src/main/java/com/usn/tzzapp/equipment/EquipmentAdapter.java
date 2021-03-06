package com.usn.tzzapp.equipment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.usn.tzzapp.databinding.EquipmentItemBinding;

import java.util.List;

/**
 * This adapter will allow the list to be shown to the user in the recycler view.
 * <p>
 * It will also allow the list to be customised to fit our needs.
 * <p>
 * Currently it uses the AsyncListDiffer to find out where items should go.
 * <p>
 * The reason behind is that this processes the items on a background thread.
 * <p>
 * So that the items are processed while the user can do other actions
 * and they wont interfere with each other
 */

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {

    /**
     * This interface is used to find out what is clicked on the screen/list
     * And what position it has .
     */
  /*  public interface OnEquipmentListener {
        void onEquipmentClick(int pos);
    }*/

    private static final DiffUtil.ItemCallback<EquipmentItem> DIFF_CALLBACK
            = new DiffUtil.ItemCallback<EquipmentItem>() {
        /**
         * @param oldItem
         * @param newItem
         * @return a boolean value if the the items are the same,
         * true if they are, false if they are not
         */
        @Override
        public boolean areItemsTheSame(
                @NonNull EquipmentItem oldItem, @NonNull EquipmentItem newItem) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldItem.getName().equals(newItem.getName());
        }

        /**
         * @param oldItem the old item that was there before it was moved/removed
         * @param newItem the new item that is there now / inserted
         * @return a boolean value if the the items/content are the same,
         * true if they are, false if they are not
         */
        //@SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(
                @NonNull EquipmentItem oldItem, @NonNull EquipmentItem newItem) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            if (!(oldItem.getName() == null || newItem.getName() == null)) {
                return oldItem.equals(newItem);
            }
            return true;
        }
    };
    //private OnEquipmentListener onEquipmentListener;
    private final AsyncListDiffer<EquipmentItem> mDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);


    /*  public EquipmentAdapter(List<EquipmentItem> list, OnEquipmentListener onEquipmentListener) {

          this.equipmentItemList = list;
          //this.onEquipmentListener = onEquipmentListener;


      }*/
    public EquipmentAdapter(List<EquipmentItem> list) {
        mDiffer.submitList(list);
        setHasStableIds(true);
    }

    /**
     * @return the list of items
     */
    List<EquipmentItem> getEquipmentItemList() {
        return mDiffer.getCurrentList();
    }


    /**
     * This will let the DiffUtil and AsyncListDiffer know what list to process
     *
     * @param list the list that should be pushed out to the recycler view
     */
    public void submitList(List<EquipmentItem> list) {
        mDiffer.submitList(list);
    }

    /**
     * @param parent
     * @param viewType
     * @return the view holder with the itemBinding in it
     */
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
     * @param position This will using the @position find out where to place the items from the list on the users screen
     *                 <p>
     *                 It also using the @mSelectionTracker find out what items are selected and what items are not.
     *                 <p>
     *                 Before the @holder can bind them to the screen
     */
    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        EquipmentItem equipmentItem = mDiffer.getCurrentList().get(position);

        holder.bind(equipmentItem, position);
    }

    /**
     * @param position the position of an item in the recycler view
     * @return id of the equipment item that is in that current position
     */
    @Override
    public long getItemId(int position) {
        EquipmentItem equipmentItem = mDiffer.getCurrentList().get(position);
        return equipmentItem.getId();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * This gives the list the number of what item to put where on the screen.
     */
    @Override
    public int getItemCount() {
        return mDiffer.getCurrentList().size();
    }

    static class EquipmentViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

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

        /**
         * @param binding where the individual items are stored / showed to the user
         */
        public EquipmentViewHolder(EquipmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            equipmentItemDetails = new EquipmentItemDetails();
        }

        /**
         * Gets the items from the list (Here : equipmentItemList ),
         * that was given when the adapter was created,
         * and pushes the requested values out to the screen.
         * <p>
         * These values/strings can be set in the item  xml file (this case equipment_item.xml)
         * But they also listen to what the value of the object in that position
         * from the list it was given during creation of the adapter
         * <p>
         * This will also show the result of the selection tracker using
         *
         * @itemView.setActivated(item.isSelected()); This will then, send the value to the ColorState in res/color/item_color.xml
         */
        public void bind(EquipmentItem item, int pos) {

            /*
             *  Without these, the selection will only work for the first item in the list
             *  and it crash allow if the user starts the selection
             *  on any other item than the first.
             */
            equipmentItemDetails.pos = pos;
            equipmentItemDetails.identifier = item.getId();

            binding.setEquipmentItem(item);
            itemView.setActivated(item.isSelected());
            binding.executePendingBindings();


        }

        public ItemDetailsLookup.ItemDetails<Long> getEquipmentItemDetails(MotionEvent motionEvent) {
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
