package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomRelativeLayout;
import com.feerlaroc.invoices.screen.CustomerDetailScreen;

import org.feerlaroc.widgets.monthbar.MonthsBar;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by root on 2016/02/27.
 */
public class CustomerDetailView extends CustomRelativeLayout<CustomerDetailScreen.Presenter> {

    @Inject
    protected CustomerDetailScreen.Presenter presenter;

    CustomerDetailHeaderView mCustomerDetailHeaderView;
    TransactionListView mTransactionListView;
    private MonthsBar mMonthsBar;

    public CustomerDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        mCustomerDetailHeaderView = (CustomerDetailHeaderView) View
                .inflate(getContext(), R.layout.layout_customer_detail_header, null);

        mTransactionListView = (TransactionListView) View
                .inflate(getContext(), R.layout.layout_transaction_list, null);

        mMonthsBar = (MonthsBar) findViewById(R.id.months_bar);

    }

    public MonthsBar getMonthsBar() {
        return mMonthsBar;
    }

    public void refreshMonthsBar(){
        mMonthsBar.invalidate();
    }

    @Override
    public CustomerDetailScreen.Presenter getPresenter() {
        return presenter;
    }
}
