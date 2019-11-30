package com.haerul.mapples.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;
import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseFragment;
import com.haerul.mapples.base.BaseViewModel;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.data.entity.GenericReferences;
import com.haerul.mapples.data.entity.User;
import com.haerul.mapples.databinding.FragmentAccountBinding;
import com.haerul.mapples.ui.login.LoginActivity;
import com.haerul.mapples.ui.settings.SettingAccount;
import com.haerul.mapples.util.Constants;
import com.haerul.mapples.util.SQLiteDownloaderActivity;
import com.haerul.mapples.util.Util;

import javax.inject.Inject;

public class AccountFragment extends BaseFragment<FragmentAccountBinding, BaseViewModel> {
    
    @Inject
    MasterRepository repository;
    FragmentAccountBinding binding;
    
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = getViewDataBinding();
        User user = repository.getUserBySID(Util.getStringPreference(getBaseActivity(), Constants.USER_SID));
        GenericReferences reg = repository.getRefBySID(user.user_unit);
        binding.setItem(repository.getUserBySID(Util.getStringPreference(getBaseActivity(), Constants.USER_SID)));
        binding.region.setText(reg.ref_name);
        binding.logout.setOnClickListener(this::logout);

        binding.settings.setOnClickListener(v -> {
            startActivity(new Intent(getBaseActivity(), SettingAccount.class));
        });

        binding.reset.setOnClickListener(v -> {
            syncServer();
        });

        
    }

    private void syncServer() {
        LayoutInflater li = (LayoutInflater) getBaseActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert li != null;
        View views = li.inflate(R.layout.dialog_confirm_sync, null);
        Button submit = views.findViewById(R.id.save);
        Button cancel = views.findViewById(R.id.close);

        BottomSheetDialog dialog = new BottomSheetDialog(getBaseActivity(), R.style.DialogStyle);

        submit.setOnClickListener((v2) -> {
            dialog.dismiss();
            Toast.makeText(getBaseActivity(), "Please wait...", Toast.LENGTH_SHORT).show();
            getBaseActivity().showProgress();
            new Handler().postDelayed(() -> {
                getBaseActivity().hideProgress();
                Intent intent = new Intent(getBaseActivity(), SQLiteDownloaderActivity.class);
                startActivity(intent);
                getBaseActivity().finish();
                System.exit(0);
            }, 300);
        });
        cancel.setOnClickListener((v2) -> dialog.dismiss());

        dialog.setContentView(views);
        dialog.show();
    }

    private void logout(View v1) {
        View view = getLayoutInflater().inflate(R.layout.dialog_confirm_logout, null);
        Button delete = view.findViewById(R.id.delete_dialog);
        Button close = view.findViewById(R.id.close_dialog);

        BottomSheetDialog dialog = new BottomSheetDialog(getBaseActivity(), R.style.DialogStyle);

        delete.setOnClickListener(v -> {
            getBaseActivity().showProgress();
            new Handler().postDelayed(() -> {
                getBaseActivity().hideProgress();
                Util.putPreference(getBaseActivity(), Constants.IS_LOGIN, false);
                LoginActivity.navigateToLogin(getBaseActivity());
                getBaseActivity().finish();
                System.exit(0);
                Snackbar.make(binding.getRoot(), "Logging out!", Snackbar.LENGTH_SHORT).show();
            }, 200);

            dialog.dismiss();
        });

        close.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.setContentView(view);
        dialog.show();
    }
}
