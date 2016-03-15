package com.feerlaroc.invoices.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.TransactionListScreen;

import javax.inject.Inject;

/**
 * Created by root on 2016/02/27.
 */
public class TransactionListView extends CustomLinearLayout<TransactionListScreen.Presenter> {

    @Inject
    protected TransactionListScreen.Presenter mPresenter;

    RecyclerView mRecyclerView;

    public TransactionListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_transactions);

    }

    public TransactionListScreen.Presenter getmPresenter() {
        return mPresenter;
    }

    public RecyclerView getTransactionsRecyclerView() {
        return mRecyclerView;
    }

}
