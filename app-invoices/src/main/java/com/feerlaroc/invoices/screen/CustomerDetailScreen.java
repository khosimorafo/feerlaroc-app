package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.view.CustomerDetailHeaderView;
import com.feerlaroc.invoices.view.CustomerDetailView;
import com.feerlaroc.invoices.view.TransactionListView;
import com.feerlaroc.zohos.controller.ZohoApi;
import com.feerlaroc.zohos.schema.callback.ZohoApiService;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.schema.pojo.Contact;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Layout(R.layout.screen_customer_detail)
@WithModule(CustomerDetailScreen.Module.class)
public class CustomerDetailScreen extends Path {

    @dagger.Module(injects = {CustomerDetailView.class, CustomerDetailHeaderView.class,
            TransactionListView.class}, addsTo = ActivityModule.class)

    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<CustomerDetailView>{

        ZohoApiService mService = ZohoApi.getInstance().get();
        String mKey =  new Contact().DBKey();
        Map<String, Object> mContact = new HashMap<>();

        @Inject
        public Presenter(){}

        @Override
        protected void onLoad(Bundle savedInstanceState) {
            super.onLoad(savedInstanceState);
            getCustomerData();
        }

        private void getCustomerData(){

            mContact = AppDataHolder.getInstance().getEntity(Contact.class);

            Call<Object> call = mService.get(mKey, Constants.ZOHO.API,
                    Constants.ZOHO.VERSION, mContact.get("contact_id").toString(),
                    Constants.ZOHO.AUTHTOKEN, Constants.ZOHO.ORGANIZATION_ID);

            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    mContact = (Map<String, Object>) ((Map<String, Object>) response.body()).get("contact");
                    AppDataHolder.getInstance().setEntity(mContact, Contact.class);
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            });


        }
    }
}
