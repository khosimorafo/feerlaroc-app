package com.feerlaroc.invoices.view.item;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomFrameLayout;
import com.feerlaroc.invoices.screen.item.ItemScreen;

import javax.inject.Inject;


public class ItemView extends CustomFrameLayout<ItemScreen.Presenter> {

    @Inject
    ItemScreen.Presenter mPresenter;

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public ItemScreen.Presenter getPresenter() {
        return mPresenter;
    }

}
