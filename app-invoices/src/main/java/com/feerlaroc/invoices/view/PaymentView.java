package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.PaymentScreen;


public class PaymentView extends CustomLinearLayout<PaymentScreen.Presenter> {

    private PaymentScreen.Presenter mPresenter;

    public PaymentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public PaymentScreen.Presenter getmPresenter() {
        return mPresenter;
    }
}
