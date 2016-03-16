package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.PaymentScreen;

import javax.inject.Inject;

import butterknife.InjectView;


public class PaymentView extends CustomLinearLayout<PaymentScreen.Presenter> {

    @Inject
    PaymentScreen.Presenter mPresenter;

    @InjectView(R.id.pay_the_money)
    Button mButtonPay;

    public PaymentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        mButtonPay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.makePayment();
            }
        });
    }


    @Override
    public PaymentScreen.Presenter getmPresenter() {
        return mPresenter;
    }


}
