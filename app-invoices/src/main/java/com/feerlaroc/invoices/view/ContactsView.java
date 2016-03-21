package com.feerlaroc.invoices.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.widget.CustomLinearLayout;
import com.feerlaroc.invoices.screen.ContactsScreen;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by root on 2016/02/22.
 */
public class ContactsView extends CustomLinearLayout<ContactsScreen.Presenter> {

    @Inject
    ContactsScreen.Presenter mPresenter;

    //@Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public ContactsView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.inject(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

    }

    public ContactsScreen.Presenter getPresenter() {
        return mPresenter;
    }


    public RecyclerView getContactsRecyclerView() {
        return mRecyclerView;
    }
}
