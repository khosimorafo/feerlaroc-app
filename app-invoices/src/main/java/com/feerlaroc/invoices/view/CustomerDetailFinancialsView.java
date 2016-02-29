package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomFrameLayout;
import com.feerlaroc.invoices.screen.CustomerDetailFinancialsScreen;

import javax.inject.Inject;

public class CustomerDetailFinancialsView extends CustomFrameLayout<CustomerDetailFinancialsScreen.Presenter> {

    @Inject
    protected CustomerDetailFinancialsScreen.Presenter presenter;

    public CustomerDetailFinancialsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public CustomerDetailFinancialsScreen.Presenter getPresenter() {
        return presenter;
    }
}
