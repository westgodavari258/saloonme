package com.saloonme.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saloonme.R;


public class CartFragment extends BaseFragment {

    private View view;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }
}