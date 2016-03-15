package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomRelativeLayout;
import com.feerlaroc.invoices.screen.CustomerDetailScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class CustomerDetailView extends CustomRelativeLayout<CustomerDetailScreen.Presenter> {

    @Inject
    protected CustomerDetailScreen.Presenter mPresenter;

    CustomerDetailHeaderView mCustomerDetailHeaderView;
    CustomerDetailFinancialsView mCustomerDetailFinancialsView;

    public CustomerDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        mCustomerDetailHeaderView = (CustomerDetailHeaderView) View
                .inflate(getContext(), R.layout.layout_customer_detail_header, null);

        mCustomerDetailFinancialsView = (CustomerDetailFinancialsView) View
                .inflate(getContext(), R.layout.layout_customer_detail_financials, null);


    }


    public CustomerDetailScreen.Presenter getmPresenter() {
        return mPresenter;
    }
}
