package com.feerlaroc.invoices.screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.adapters.ContactHolder;
import com.feerlaroc.invoices.adapters.ContactsAdapter;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.ContactsView;
import com.feerlaroc.zohos.schema.pojo.Contact;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/02/22.
 */

@Layout(R.layout.screen_contacts)
@WithModule(ContactsScreen.Module.class)
public class ContactsScreen extends Path {


    @dagger.Module(injects = ContactsView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ContactsView>
                implements FrameworkCompletionListener, ContactHolder.SelectedItemListener{

        @Inject
        public Presenter() {}

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            if(getView() == null) return;

            getView().getContactsRecyclerView().setLayoutManager(layoutManager);

            ContactsAdapter mAdapter = new ContactsAdapter(Contact.class, R.layout.row_contant,
                    ContactHolder.class, this);

            getView().getContactsRecyclerView().setAdapter(mAdapter);

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }

        @Override
        public void onItemClick(int position) {

        }
    }

}
