package com.feerlaroc.invoices.screen;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.adapters.TransactionHolder;
import com.feerlaroc.invoices.adapters.TransactionsAdapter;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.TransactionListView;
import com.feerlaroc.zohos.schema.pojo.Contact;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.layout_transaction_list)
@WithModule(TransactionListScreen.Module.class)
public class TransactionListScreen extends Path {

    @dagger.Module(injects = TransactionListView.class, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<TransactionListView>
                                    implements TransactionHolder.SelectedItemListener{

        TransactionsAdapter mAdapter;
        Map<String, Object> mContact;
        Map<String, Object> mSelectedTransaction;



        @Inject
        public Presenter(){
            mContact = AppDataHolder.getInstance().getEntityAsMap(Contact.class);
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);

            LinearLayoutManager layoutManager = new LinearLayoutManager(getView().getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            if(getView() == null) return;

            getView().getTransactionsRecyclerView().setLayoutManager(layoutManager);

            mAdapter = new TransactionsAdapter(mContact.get("contact_id").toString(),
                    R.layout.row_transaction, TransactionHolder.class, this);

            getView().getTransactionsRecyclerView().setAdapter(mAdapter);

        }

        @Override
        public void onItemClick(int position) {

            /*mSelectedTransaction = mAdapter.getItem(position);
            AppDataHolder.getInstance().setEntity(mSelectedTransaction, TransactionSnapshot.class);
            Flow.get(getView()).set(new PaymentScreen());
*/
        }

    }
}
