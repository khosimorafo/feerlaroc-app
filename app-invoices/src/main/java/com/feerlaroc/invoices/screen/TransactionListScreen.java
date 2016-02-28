package com.feerlaroc.invoices.screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.adapters.TransactionHolder;
import com.feerlaroc.invoices.adapters.TransactionsAdapter;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.TransactionListView;

import javax.inject.Inject;
import javax.inject.Singleton;

import mortar.ViewPresenter;

@Layout(R.layout.layout_transaction_list)
@WithModule(TransactionListScreen.Module.class)
public class TransactionListScreen {

    @dagger.Module(injects = {TransactionListView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<TransactionListView>
                                    implements TransactionHolder.SelectedItemListener{

        TransactionsAdapter mAdapter;

        @Inject
        public Presenter(){
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            if(getView() == null) return;

            getView().getTransactionsRecyclerView().setLayoutManager(layoutManager);

            mAdapter = new TransactionsAdapter("", "", R.layout.row_transaction, TransactionHolder.class, this);

            getView().getTransactionsRecyclerView().setAdapter(mAdapter);
        }

        @Override
        public void onItemClick(int position) {

        }
    }
}
