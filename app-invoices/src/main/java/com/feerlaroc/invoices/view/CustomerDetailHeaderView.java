package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.CustomerDetailHeaderScreen;

/**
 * Created by root on 2016/02/27.
 */
public class CustomerDetailHeaderView extends CustomLinearLayout<CustomerDetailHeaderScreen.Presenter> {

    CustomerDetailHeaderScreen.Presenter presenter;

    public CustomerDetailHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public CustomerDetailHeaderScreen.Presenter getPresenter() {
        return presenter;
    }
}
