package com.haerul.mapples.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.haerul.mapples.R;

import dagger.android.AndroidInjection;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity
        implements BaseFragment.Callback {

    private T binding;
    private V viewModel;
    private ProgressDialog progressDialog;
    private String tag = null;

    public abstract int getBindingVariable();

    public abstract @LayoutRes int getLayoutId();

    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDependencyInjection();
        performDataBinding();
        progressDialog = new ProgressDialog(this, R.style.ProgressDialogStyle);
        progressDialog.setMessage("Please wait... ");
        progressDialog.setCancelable(false);
        hideKeyboard();
    }

    public T getViewDataBinding() {
        return binding;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    
    public void showProgress() {
        if (progressDialog != null)
            progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }


    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
    }

    
    public boolean isNetworkConnected() {
//        return Util.isNetworkConnected(getApplicationContext());
        return false;
    }

    public void openActivityOnTokenExpire() {
//        Toast.makeText(this, "Token Expired!", Toast.LENGTH_SHORT).show();
//        Util.putPreference(this, Constants.IS_LOGIN, false);
//        startActivity(LoginActivity.newIntent(this));
//        finish();
    }

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void checkLogin() {
//        if (!Util.getBooleanPreference(this, Constants.IS_LOGIN)) {
//            startActivity(LoginActivity.newIntent(this));
//            finish();
//        }
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
