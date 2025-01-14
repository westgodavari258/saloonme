package com.saloonme.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.athbk.ultimatetablayout.OnClickTabListener;
import com.athbk.ultimatetablayout.UltimateTabLayout;
import com.saloonme.R;
import com.saloonme.interfaces.ISaloonServiceView;
import com.saloonme.interfaces.StringConstants;
import com.saloonme.model.response.AddCartResponse;
import com.saloonme.model.response.RemoveCartResponse;
import com.saloonme.model.response.SaloonServiceResponse;
import com.saloonme.model.response.SaloonSubServiceResponse;
import com.saloonme.network.APIClient;
import com.saloonme.presenters.SaloonServicePresenter;
import com.saloonme.ui.adapters.FragmentAdapterDemo;

import butterknife.BindView;
import butterknife.OnClick;

public class CategoryFilterActivity extends BaseAppCompactActivity implements
        ISaloonServiceView {

    private UltimateTabLayout tabLayout;
    private ViewPager viewPager;
    private String saloonId;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.tv_heading)
    TextView tv_heading;

    @Override
    public int getActivityLayout() {
        return R.layout.activity_category_filter;
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.continue_btn)
    void onContinueClick() {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstants.EXTRA_DETAILS, saloonId);
        goToActivity(BookActivity.class, bundle);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick(R.id.iv_menu)
    void onBackClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SaloonServicePresenter saloonServicePresenter = new SaloonServicePresenter(APIClient.getAPIService(),
                this);
        saloonId = getIntent().getStringExtra(StringConstants.EXTRA_DETAILS);
        if (saloonId.isEmpty()) {
            showToast(getString(R.string.something_went_wrong));
            finish();
        }
        tv_heading.setText(R.string.select_service);
        tabLayout = findViewById(R.id.verticalTabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setOnClickTabListener(new OnClickTabListener() {
            @Override
            public void onClickTab(int currentPos) {
                Log.e("LOG", "OnClickTab " + currentPos);
                viewPager.setCurrentItem(currentPos);
                /*if (currentPos == 1) {
                    tabLayout.setNumberBadge(currentPos, 0);
                } else {
                    tabLayout.setNumberBadge(currentPos, 1);
                }*/
            }
        });


        saloonServicePresenter.getSaloonMainServices(saloonId);
    }

    @Override
    public void saloonServiceSuccess(SaloonServiceResponse saloonServiceResponse) {
        if (saloonServiceResponse == null) {
            showToast("Failed to get the services");
            return;
        }
        if (saloonServiceResponse.getStatus().contains("fail")) {
            showToast(saloonServiceResponse.getMessage());
            return;
        }
        if (saloonServiceResponse.getData() == null || saloonServiceResponse.getData().size() == 0) {
            showToast(saloonServiceResponse.getMessage());
            return;
        }
        FragmentAdapterDemo adapter = new FragmentAdapterDemo(getSupportFragmentManager(),
                saloonServiceResponse.getData(), saloonId);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager, adapter);
    }

    @Override
    public void saloonServiceFailed() {
        showToast("Failed to get the services");
    }

    @Override
    public void saloonSubServiceSuccess(SaloonSubServiceResponse saloonSubServiceResponse) {

    }

    @Override
    public void saloonSubServiceFailed() {

    }

    @Override
    public void addToCartSuccess(AddCartResponse addCartResponse) {

    }

    @Override
    public void addToCartFailed() {

    }

    @Override
    public void removeCartSuccess(RemoveCartResponse removeCartResponse) {

    }

    @Override
    public void removeCartFailed() {

    }
}