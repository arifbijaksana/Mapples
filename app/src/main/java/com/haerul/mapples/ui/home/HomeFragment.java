package com.haerul.mapples.ui.home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseFragment;
import com.haerul.mapples.data.api.ConnectionServer;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.data.entity.User;
import com.haerul.mapples.databinding.FragmentHomeBinding;
import com.haerul.mapples.util.Constants;
import com.haerul.mapples.util.Util;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    @Inject
    ConnectionServer server;
    @Inject
    MasterRepository repository;
    
    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
       
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel = ViewModelProviders.of(this, new HomeViewModel.ModelFactory(getBaseActivity(), server, repository)).get(HomeViewModel.class);
        User user = repository.getUserBySID(Util.getStringPreference(getBaseActivity(), Constants.USER_SID));
    }
}