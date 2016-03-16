package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.application.InvoiceApp;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.controller.payments.PaymentManager;
import com.feerlaroc.invoices.view.PaymentView;
import com.feerlaroc.zohos.listener.ZohoCallCompletionListener;
import com.feerlaroc.zohos.model.CustomerPaymentRequest;
import com.feerlaroc.zohos.schema.types.InvoiceID;

import org.feerlaroc.widgets.rangebar.PinView;

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
    public static class Presenter extends ViewPresenter<PaymentView>
            implements ZohoCallCompletionListener {

        PaymentManager mPaymentManager;
        App app = new InvoiceApp();
        InvoiceID mInvoiceID;
        PinView mPin;


        @Inject
        public Presenter() {

            mPin = (PinView) AppDataHolder.getInstance().getEntity(PinView.class);

        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {

            super.onLoad(savedInstanceState);
            mPaymentManager = new PaymentManager(getView().getContext());


        }

        public void makePayment(){

            mInvoiceID = new InvoiceID(mPin.getTag());

            mPaymentManager.process(mInvoiceID, 300.00);

            save();
        }

        private void save(){

            try {

                app.create(CustomerPaymentRequest.class, mPaymentManager.getObject(), this);

            } catch (FrameworkException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onSuccess(Object o) {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }
}
