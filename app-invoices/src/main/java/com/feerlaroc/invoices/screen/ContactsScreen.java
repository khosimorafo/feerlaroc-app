package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.entity.EntityInterface;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.adapters.ContactHolder;
import com.feerlaroc.invoices.adapters.ContactsAdapter;
import com.feerlaroc.invoices.application.InvoiceApp;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.ContactsView;
import com.feerlaroc.zohos.schema.pojo.Contact;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;
import rx.Observable;

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
                implements FrameworkCompletionListener{

        App app = new InvoiceApp();

        @Inject
        public Presenter() {}

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);

            Observable<List<EntityInterface>> observable = app.get(Contact.class);

            ContactsAdapter mAdapter = new ContactsAdapter(Contact.class, R.layout.row_contant,
                    ContactHolder.class, observable);

            getView().getContactsRecyclerView().setAdapter(mAdapter);

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}
