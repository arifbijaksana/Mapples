package com.haerul.mapples.data.db.repository;


import androidx.lifecycle.LiveData;

import com.haerul.mapples.data.db.MasterDatabase;
import com.haerul.mapples.data.entity.Base64Data;
import com.haerul.mapples.data.entity.DataTS;
import com.haerul.mapples.data.entity.GenericReferences;
import com.haerul.mapples.data.entity.User;

import java.util.List;

public class MasterRepository {
    
    private final MasterDatabase database;
    
    public MasterRepository(MasterDatabase database) {
        this.database = database;
    }

    public static MasterRepository getInstance(MasterDatabase database) {
        return new MasterRepository(database);
    }

    //reff generic
    public List<GenericReferences> getRefByCategory(String category) {
        return database.genericReferencesDao().getGenericRefByCategory(category);
    }
    public List<GenericReferences> getRefByCategory(String category, String where) {
        return database.genericReferencesDao().getGenericRefByCategory(category, where);
    }
    public GenericReferences getRefBySID(String sid) {
        return database.genericReferencesDao().getRefBySID(sid);
    }

    public GenericReferences getRefByValue(String cat, int val) {
        return database.genericReferencesDao().getRefByValue(cat, val);
    }

    public User getUserBySID(String sid) {
        return database.userDao().getUserBySID(sid);
    }

    // base64data
    public void insertBase64Data(Base64Data data) {
        database.base64DataDao().insertBase64Data(data);
    }

    public void updateStatus(String sid, boolean status) {
        database.base64DataDao().updateStatus(sid, status);
    }

    public Base64Data getBase64Data(String sid) {
        return database.base64DataDao().getBase64Data(sid);
    }

    public void updateBase64Data(Base64Data data) {
        database.base64DataDao().updateData(data);
    }

    // inspeksi
    public void insertDataTs(DataTS dataTS) {
        database.dataTsDao().insertDataTs(dataTS);
    }

    public void updateDataTs(DataTS dataTS) {
        database.dataTsDao().updateDataTs(dataTS);
    }

    public DataTS getLastDataTs() {
        return database.dataTsDao().getLastDataTs();
    }

    public LiveData<List<DataTS>> getAllDataTs() {
        return database.dataTsDao().getAllDataTs();
    }
}
