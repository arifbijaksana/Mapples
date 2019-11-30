package com.haerul.mapples.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.haerul.mapples.data.db.dao.Base64DataDao;
import com.haerul.mapples.data.db.dao.DataTsDao;
import com.haerul.mapples.data.db.dao.GenericCategoryDao;
import com.haerul.mapples.data.db.dao.GenericReferencesDao;
import com.haerul.mapples.data.db.dao.UserDao;
import com.haerul.mapples.data.entity.Base64Data;
import com.haerul.mapples.data.entity.DataTS;
import com.haerul.mapples.data.entity.GenericCategory;
import com.haerul.mapples.data.entity.GenericReferences;
import com.haerul.mapples.data.entity.User;
import com.haerul.mapples.util.Constants;

@Database(entities = {
        User.class,
        GenericCategory.class,
        GenericReferences.class,
        Base64Data.class,
        DataTS.class
}, version = 1, exportSchema = false)
public abstract class MasterDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract GenericCategoryDao genericCategoryDao();
    public abstract GenericReferencesDao genericReferencesDao();
    public abstract Base64DataDao base64DataDao();
    public abstract DataTsDao dataTsDao();
    
    private static MasterDatabase INSTANCE;
    public  static MasterDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (MasterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, MasterDatabase.class, Constants.MASTER_DB)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
