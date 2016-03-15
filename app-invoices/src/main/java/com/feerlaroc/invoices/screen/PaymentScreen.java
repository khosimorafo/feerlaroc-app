package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.core.listeners.FrameworkCompletionListener;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.InvoiceApp;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.ContactsView;
import com.feerlaroc.invoices.view.PaymentView;
import com.feerlaroc.zohos.model.CustomerPaymentRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;


@Layout(R.layout.screen_payments)
@WithModule(PaymentScreen.Module.class)
public class PaymentScreen extends Path {

    @dagger.Module(injects = PaymentView.class, addsTo = ActivityModule.class)
    public class Module {
    }




    @Singleton
    public static class Presenter extends ViewPresenter<ContactsView>
            implements FrameworkCompletionListener{


        App app = new InvoiceApp();
        CustomerPaymentRequest mCustomerPaymentRequest;


        @Inject
        public Presenter() {}

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);

            mCustomerPaymentRequest = CustomerPaymentRequest.getInstance(getView().getContext(), "");
            //mCustomerPaymentRequest.

        }

        private void save(){

            try {

                app.create(CustomerPaymentRequest.class, mCustomerPaymentRequest, this);

            } catch (FrameworkException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }
}
