package com.haerul.mapples.di.module;


import androidx.room.Room;

import com.haerul.mapples.App;
import com.haerul.mapples.data.db.MasterDatabase;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.util.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class  AppDatabaseModule {

    @Provides @Singleton
    public MasterRepository provideAccountRepository(App application) {
        MasterDatabase database = Room.databaseBuilder(application, MasterDatabase.class, Constants.MASTER_DB)
                .allowMainThreadQueries()
                .build();
        return MasterRepository.getInstance(database);
    }
}
