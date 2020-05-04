package com.usn.tzzapp.equiment;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EquipmentViewModel extends AndroidViewModel {

    private EquipmentRepository equipmentRepository;

    private LiveData<List<EquipmentItem>> listLiveData;

    private LiveData<EquipmentItem> equipmentItemLiveData;

    public EquipmentViewModel(Application application){
        super(application);
        equipmentRepository = new EquipmentRepository(application);
        listLiveData = equipmentRepository.getAllEquipment();
    }

    LiveData<List<EquipmentItem>> getAllEquipment() { return  listLiveData; }

    LiveData<EquipmentItem> getEquipmentItem(String id) {
        equipmentItemLiveData = equipmentRepository.getEquipmentItem(id);
        return equipmentItemLiveData ; }


    void insert(EquipmentItem equipmentItem){
        equipmentRepository.insert(equipmentItem);
    }

    void delete(EquipmentItem equipmentItem){
        equipmentRepository.delete(equipmentItem);
    }
}
