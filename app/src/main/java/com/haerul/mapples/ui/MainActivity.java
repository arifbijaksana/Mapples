package com.haerul.mapples.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseActivity;
import com.haerul.mapples.base.BaseViewModel;
import com.haerul.mapples.databinding.ActivityMainBinding;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseActivity<ActivityMainBinding, BaseViewModel> {

    public static final String STARTFOREGROUND_ACTION = "com.haerul.mapples.start.foreground";
    public static final String STOPFOREGROUND_ACTION = "com.haerul.mapples.stop.foreground";
    
    private ActivityMainBinding binding;
    
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        getSupportActionBar().setElevation(0);
        setupNavBottom();
    }

    private void setupNavBottom() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_log, R.id.navigation_account)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static void navigateToMain(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

}
