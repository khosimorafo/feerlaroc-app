package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.CustomerDetailFinancialsView;
import com.feerlaroc.zohos.schema.pojo.Contact;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.layout_customer_detail_financials)
@WithModule(CustomerDetailFinancialsScreen.Module.class)
public class CustomerDetailFinancialsScreen extends Path{

    @dagger.Module(injects = {CustomerDetailFinancialsView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<CustomerDetailFinancialsView> {

        Map<String, Object> mContact = new HashMap<>();
        @Inject
        public Presenter(){
            mContact = AppDataHolder.getInstance().getEntity(Contact.class);
        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
        }

    }

}
