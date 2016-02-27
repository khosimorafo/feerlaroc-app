package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomRelativeLayout;
import com.feerlaroc.invoices.screen.CustomerDetailScreen;

/**
 * Created by root on 2016/02/27.
 */
public class CustomerDetailView extends CustomRelativeLayout<CustomerDetailScreen.Presenter> {

    CustomerDetailScreen.Presenter presenter;

    public CustomerDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public CustomerDetailScreen.Presenter getPresenter() {
        return presenter;
    }
}
