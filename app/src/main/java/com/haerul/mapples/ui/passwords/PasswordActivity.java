package com.haerul.mapples.ui.passwords;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseActivity;
import com.haerul.mapples.data.api.ConnectionServer;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.databinding.ActivityChangePasswordBinding;
import com.haerul.mapples.ui.update_profile.UpdateActivity;

import javax.inject.Inject;

public class PasswordActivity  extends BaseActivity <ActivityChangePasswordBinding, PasswordViewModel>{

   private ActivityChangePasswordBinding binding ;
    private PasswordViewModel viewModel ;

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
        return R.layout.activity_change_password;
    }

    @Override
    public PasswordViewModel getViewModel() {
        return viewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();

        setSupportActionBar(binding.toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true ;


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }


}
