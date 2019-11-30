package com.haerul.mapples.ui.data_ts;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.haerul.mapples.R;
import com.haerul.mapples.base.BaseFragment;
import com.haerul.mapples.data.api.ConnectionServer;
import com.haerul.mapples.data.db.repository.MasterRepository;
import com.haerul.mapples.databinding.FragmentDataTsBinding;

import javax.inject.Inject;

public class DataTsFragment extends BaseFragment<FragmentDataTsBinding, DataTsViewModel> {

    @Inject
    ConnectionServer server;
    @Inject
    MasterRepository repository;
    
    private FragmentDataTsBinding binding;
    private DataTsViewModel viewModel;
    
    @Override
    public int getBindingVariable() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_data_ts;
    }

    @Override
    public DataTsViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewDataBinding();
        viewModel = ViewModelProviders.of(this, new DataTsViewModel.ModelFactory(getBaseActivity(), server, repository)).get(DataTsViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}