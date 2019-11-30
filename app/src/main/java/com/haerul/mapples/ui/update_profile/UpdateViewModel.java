package com.haerul.mapples.ui.update_profile;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.haerul.mapples.base.BaseViewModel;
import com.haerul.mapples.data.api.ConnectionServer;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.ui.log.LogViewModel;

public class UpdateViewModel extends BaseViewModel {


    public UpdateViewModel(Context context, ConnectionServer connectionServer, MasterRepository repository) {
        super(context, connectionServer, repository);
    }

    public static class ModelFactory implements ViewModelProvider.Factory {
        private Context context;
        private ConnectionServer server;
        private MasterRepository repository;
        public ModelFactory(Context context, ConnectionServer server, MasterRepository repository) {
            this.context = context;
            this.server = server;
            this.repository = repository;
        }
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new UpdateViewModel(context, server, repository);
        }
    }
}

