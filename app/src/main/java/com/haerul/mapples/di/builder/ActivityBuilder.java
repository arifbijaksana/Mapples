package com.haerul.mapples.di.builder;

import com.haerul.mapples.ui.MainActivity;
import com.haerul.mapples.ui.account.AccountFragment;
import com.haerul.mapples.ui.data_ts.DataTsFragment;
import com.haerul.mapples.ui.home.HomeFragment;
import com.haerul.mapples.ui.log.LogFragment;
import com.haerul.mapples.ui.login.LoginActivity;
import com.haerul.mapples.ui.passwords.PasswordActivity;
import com.haerul.mapples.ui.settings.SettingAccount;
import com.haerul.mapples.ui.update_profile.UpdateActivity;
import com.haerul.mapples.util.CameraXActivity;
import com.haerul.mapples.util.MapActivity;
import com.haerul.mapples.util.SQLiteDownloaderActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder { 
   
   @ContributesAndroidInjector
   abstract MainActivity mainActivity();

   @ContributesAndroidInjector
   abstract UpdateActivity updateActivity();

   @ContributesAndroidInjector
   abstract SettingAccount settingAccount();

   @ContributesAndroidInjector
   abstract PasswordActivity passwordActivity();
   
   @ContributesAndroidInjector
   abstract LoginActivity loginActivity();
   
   @ContributesAndroidInjector
   abstract SQLiteDownloaderActivity sqLiteDownloaderActivity();
   
   @ContributesAndroidInjector
   abstract CameraXActivity cameraXActivity();
   
   @ContributesAndroidInjector
   abstract MapActivity mapActivity();
   
   @ContributesAndroidInjector
   abstract HomeFragment homeFragment();
   
   @ContributesAndroidInjector
   abstract DataTsFragment dataTsFragment();
   
   @ContributesAndroidInjector
   abstract LogFragment logFragment();
   
   @ContributesAndroidInjector
   abstract AccountFragment accountFragment();
   
   
}
