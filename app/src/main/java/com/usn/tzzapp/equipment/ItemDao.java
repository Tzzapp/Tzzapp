package com.usn.tzzapp.equipment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    /**
     * @param equipmentItem the item that is to be inserted in to the database
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EquipmentItem equipmentItem);

    /**
     * @return the list of the all the items in the data base so that they can be shown to the user
     */
    @Query("SELECT * from equipment_table ORDER BY prod_id ASC")
    LiveData<List<EquipmentItem>> getEquipment();

    /**
     * @param id the id of the item that you want ot read/edit in the database
     * @return a live data version of the item requested, so that item can be observed and made changes to
     */
    @Query("SELECT * from equipment_table WHERE  id=:id")
    LiveData<EquipmentItem> getEquipmentItem(String id);

    /**
     * A SQL query to delete all the items in the equipment_table
     */
    @Query("DELETE FROM equipment_table")
    void deleteAll();

    /**
     * A SQL update query to update the @item in the database
     */
    @Update()
    void updateItem(EquipmentItem item);

    /**
     * A SQL delete query to remove the @item from the database
     */
    @Delete()
    void deleteItem(EquipmentItem item);
}
