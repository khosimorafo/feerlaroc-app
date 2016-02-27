package com.feerlaroc.invoices.screen;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.CustomerDetailHeaderView;

import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.screen_customer_detail_header)
@WithModule(CustomerDetailHeaderScreen.Module.class)
public class CustomerDetailHeaderScreen extends Path {

    @dagger.Module(injects = {CustomerDetailHeaderView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<CustomerDetailHeaderView> {

    }
}