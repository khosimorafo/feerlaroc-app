package com.feerlaroc.invoices.view;

import android.content.Context;
import android.util.AttributeSet;

import com.feerlaroc.invoices.ContactsScreen;
import com.feerlaroc.moonmonkeylabs.common.widget.CustomLinearLayout;

/**
 * Created by root on 2016/02/22.
 */
public class ContactsView extends CustomLinearLayout<ContactsScreen.Presenter> {

    ContactsScreen.Presenter presenter;

    public ContactsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public ContactsScreen.Presenter getPresenter() {
        return presenter;
    }
}
