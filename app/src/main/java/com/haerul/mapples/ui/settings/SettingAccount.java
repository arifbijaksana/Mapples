package com.haerul.mapples.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseActivity;
import com.haerul.mapples.data.api.ConnectionServer;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.databinding.ActivitySettingAccountBinding;
import com.haerul.mapples.ui.passwords.PasswordActivity;
import com.haerul.mapples.ui.update_profile.UpdateActivity;

import javax.inject.Inject;

public class SettingAccount extends BaseActivity <ActivitySettingAccountBinding, SettingViewModel > {

   private ActivitySettingAccountBinding binding ;
    private SettingViewModel viewModel ;

    @Inject
    ConnectionServer server;
    @Inject
    MasterRepository repository;

    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting_account;
    }

    @Override
    public SettingViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       binding = getViewDataBinding();
       viewModel = ViewModelProviders.of(this,
                        new SettingViewModel.ModelFactory(this,
                       server, repository)).get(SettingViewModel.class);

       binding.settingAccount.setOnClickListener(view -> {
           startActivity(new Intent(this, UpdateActivity.class ));
       });

       binding.change.setOnClickListener(view -> {
           startActivity(new Intent(this, PasswordActivity.class));
       });

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


}
