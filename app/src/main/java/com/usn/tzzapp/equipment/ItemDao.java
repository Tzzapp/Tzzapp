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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(EquipmentItem equipmentItem);

    @Query("SELECT * from equipment_table ORDER BY prod_id ASC")
    LiveData<List<EquipmentItem>> getEquipment();

    @Query("SELECT * from equipment_table WHERE  id=:id")
    LiveData<EquipmentItem> getEquipmentItem(String id);

    @Query("DELETE FROM equipment_table")
    void deleteAll();

    @Update()
    void updateItem(EquipmentItem item);

    @Delete()
    void deleteItem(EquipmentItem item);
}
