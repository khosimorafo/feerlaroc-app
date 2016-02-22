package com.feerlaroc.invoices;

import com.feerlaroc.invoices.view.ContactsView;
import com.feerlaroc.moonmonkeylabs.common.flow.Layout;
import com.feerlaroc.moonmonkeylabs.common.mortarscreen.WithModule;

import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

/**
 * Created by root on 2016/02/22.
 */

@Layout(R.layout.screen_contacts)
@WithModule(ContactsScreen.Module.class)
public class ContactsScreen extends Path {

    @dagger.Module(injects = {ContactsView.class/*, DriverHeaderView.class, DriverGridView.class,
            DriverEditView.class*/}, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ContactsView> {

    }
}
