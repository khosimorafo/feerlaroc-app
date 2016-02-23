package com.feerlaroc.invoices.screen;

import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.ContactsView;

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
                implements FrameworkCompletionListener{

        @Inject
        public Presenter() {}

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}
