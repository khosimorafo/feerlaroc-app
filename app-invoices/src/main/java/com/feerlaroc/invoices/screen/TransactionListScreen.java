package com.feerlaroc.invoices.screen;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.TransactionListView;

import javax.inject.Singleton;

import mortar.ViewPresenter;

@Layout(R.layout.screen_transaction_list)
@WithModule(TransactionListScreen.Module.class)
public class TransactionListScreen {

    @dagger.Module(injects = {TransactionListView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<TransactionListView> {

    }
}
