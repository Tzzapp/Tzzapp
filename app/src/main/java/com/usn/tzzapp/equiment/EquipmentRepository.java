package com.usn.tzzapp.equiment;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.usn.tzzapp.AppDatabase;

import java.util.List;

class EquipmentRepository {
    private ItemDao mItemDao;
    private LiveData<List<EquipmentItem>> mAllEquipment;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples


    EquipmentRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mItemDao = db.itemDao();
        mAllEquipment = mItemDao.getEquipment();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<EquipmentItem>> getAllEquipment() {
        return mAllEquipment;
    }

    LiveData<EquipmentItem> getEquipmentItem(String id){
        return mItemDao.getEquipmentItem(id);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(EquipmentItem equipmentItem) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.insert(equipmentItem);
        });
    }

    void updateEquipmentItem(EquipmentItem equipmentItem){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.updateItem((equipmentItem));
        });

    }

    void delete(EquipmentItem equipmentItem){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mItemDao.deleteItem((equipmentItem));
        });
    }
}

