package com.haerul.mapples.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.haerul.mapples.data.entity.DataTS;

import java.util.List;

@Dao
public interface DataTsDao {
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDataTs(DataTS dataTS);

    @Query("select * from data_ts order by d_min_of_blth desc")
    LiveData<List<DataTS>> getAllDataTs();
    
    @Update
    void updateDataTs(DataTS dataTS);

    @Query("select * from data_ts order by d_min_of_blth desc LIMIT 1")
    DataTS getLastDataTs();
}
