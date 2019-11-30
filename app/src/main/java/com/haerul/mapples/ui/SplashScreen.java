package com.haerul.mapples.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;

import com.haerul.mapples.ui.login.LoginActivity;

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
