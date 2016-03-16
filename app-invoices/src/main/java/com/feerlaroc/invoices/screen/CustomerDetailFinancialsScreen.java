package com.feerlaroc.invoices.screen;

import android.os.Bundle;

import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.AppDataHolder;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.controller.FinancialMonthManager;
import com.feerlaroc.invoices.model.Month;
import com.feerlaroc.invoices.quiry.CustomerTransactionsArray;
import com.feerlaroc.invoices.view.CustomerDetailFinancialsView;
import com.feerlaroc.zohos.schema.helper.Constants;
import com.feerlaroc.zohos.schema.pojo.Contact;

import org.feerlaroc.widgets.rangebar.PinData;
import org.feerlaroc.widgets.rangebar.PinView;
import org.feerlaroc.widgets.rangebar.RangeBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.Flow;
import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.layout_customer_detail_financials)
@WithModule(CustomerDetailFinancialsScreen.Module.class)
public class CustomerDetailFinancialsScreen extends Path{

    @dagger.Module(injects = {CustomerDetailFinancialsView.class}, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<CustomerDetailFinancialsView>
                                    implements CustomerTransactionsArray.OnDataChangedListener,
                                    RangeBar.OnPinClick{

        Map<String, Object> mContact = new HashMap<>();
        CustomerTransactionsArray mCustomerTransactionsArray;

        @Inject
        public Presenter(){

            mContact = AppDataHolder.getInstance().getEntityAsMap(Contact.class);
            mCustomerTransactionsArray = new CustomerTransactionsArray(
                    Constants.ZOHO.INVOICES, mContact.get("contact_id").toString());

            mCustomerTransactionsArray.setOnChangedListener(this);


        }

        @Override
        protected void onLoad(Bundle savedInstanceState) {

            super.onLoad(savedInstanceState);
            getView().initBar(getDummieMonths());

        }

        private void updateMonthsBar(){

            mContact = AppDataHolder.getInstance().getEntityAsMap(Contact.class);

            // Draw new month bar
            getView().getMonthsBar().setAmountOutstanding((Double)
                    mContact.get("outstanding_receivable_amount"));
            getView().getMonthsBar().setPinData(getMonths());
            getView().refreshMonthsBar();
            getView().setPinListener();

            mContact = null;

        }


        private List<PinData> getMonths(){

            return FinancialMonthManager.getMonths(mCustomerTransactionsArray);

        }


        private List<PinData> getDummieMonths(){

            List<PinData> months = new ArrayList<>();

            for(int i = 0; i < 4; i++){

                Month month = new Month(new Date());
                months.add(month);

            }

            return months;
        }


        @Override
        public void onChanged() {

            AppDataHolder.getInstance().setEntity(mCustomerTransactionsArray, CustomerTransactionsArray.class);
            updateMonthsBar();

        }

        @Override
        public void onPinClicked(PinView pin) {

            AppDataHolder.getInstance().setEntity(pin, PinView.class);
            Flow.get(getView()).set(new PaymentScreen());


        }
    }


}
