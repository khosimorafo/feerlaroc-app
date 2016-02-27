package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.TransactionListScreen;

/**
 * Created by root on 2016/02/27.
 */
public class TransactionListView extends CustomLinearLayout<TransactionListScreen.Presenter> {

    TransactionListScreen.Presenter presenter;

    public TransactionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public TransactionListScreen.Presenter getPresenter() {
        return presenter;
    }
}
