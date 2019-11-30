package com.haerul.mapples.ui.update_profile;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.Nullable;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseActivity;
import com.haerul.mapples.databinding.ActivityUpdateProfileBinding;

public class UpdateActivity extends BaseActivity <ActivityUpdateProfileBinding, UpdateViewModel> {

    ActivityUpdateProfileBinding binding;
    UpdateViewModel updateViewModel ;


    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_profile;
    }

    @Override
    public UpdateViewModel getViewModel() {
        return updateViewModel;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = getViewDataBinding();
        setSupportActionBar(binding. toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return true ;


    }
}


