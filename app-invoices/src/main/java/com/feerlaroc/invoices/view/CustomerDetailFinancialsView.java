package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomFrameLayout;
import com.feerlaroc.invoices.screen.CustomerDetailFinancialsScreen;

import org.feerlaroc.widgets.monthbar.MonthsBar;
import org.feerlaroc.widgets.rangebar.PinData;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

public class CustomerDetailFinancialsView extends CustomFrameLayout<CustomerDetailFinancialsScreen.Presenter> {

    @InjectView(R.id.frame)
    FrameLayout mFrameLayout;

    MonthsBar mMonthsBar;

    @Inject
    protected CustomerDetailFinancialsScreen.Presenter mPresenter;

    public CustomerDetailFinancialsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void initBar(List<PinData> pinData){

        mMonthsBar = new MonthsBar(getContext(), pinData, 0.0);
        mMonthsBar.setId(R.id.monthsBar);
        //mMonthsBar.setBackgroundColor(ColorUtils.PAID);
        mFrameLayout.addView(mMonthsBar);

    }
    public MonthsBar getMonthsBar() {
        return mMonthsBar;
    }

    public void refreshMonthsBar(){
        mMonthsBar.invalidate();
    }

    public void setPinListener(){
        mMonthsBar.setOnPinClickedListener(getPresenter());
    }

    public CustomerDetailFinancialsScreen.Presenter getPresenter() {
        return mPresenter;
    }

}
