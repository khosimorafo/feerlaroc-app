package com.feerlaroc.invoices.view.item;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.item.ItemGridScreen;

import javax.inject.Inject;

/**
 * Created by root on 2016/03/17.
 */
public class ItemGridView extends CustomLinearLayout<ItemGridScreen.Presenter> {

    @Inject
    ItemGridScreen.Presenter mPresenter;

    public ItemGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public ItemGridScreen.Presenter getPresenter() {
        return null;
    }

}
