package com.feerlaroc.invoices.screen;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.CustomerDetailHeaderView;
import com.feerlaroc.invoices.view.CustomerDetailView;
import com.feerlaroc.invoices.view.TransactionListView;

import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.screen_customer_detail)
@WithModule(CustomerDetailScreen.Module.class)
public class CustomerDetailScreen extends Path {

    @dagger.Module(injects = {CustomerDetailView.class, CustomerDetailHeaderView.class,
            TransactionListView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<CustomerDetailView>{

    }
}
