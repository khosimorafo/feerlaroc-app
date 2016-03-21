package com.feerlaroc.invoices.screen.item;

import com.feerlaroc.core.app.App;
import com.feerlaroc.core.error.FrameworkException;
import com.feerlaroc.invoices.ActivityModule;
import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.application.InvoiceApp;
import com.feerlaroc.invoices.common.flow.Layout;
import com.feerlaroc.invoices.common.mortarscreen.WithModule;
import com.feerlaroc.invoices.schema.AddItem;
import com.feerlaroc.invoices.view.item.ItemDetailView;

import org.feerlaroc.force.listener.ForceCompletionListener;
import org.feerlaroc.force.model.ForceRequestObject;
import org.feerlaroc.force.schema.helper.Constants;
import org.json.JSONObject;

import javax.inject.Inject;
import javax.inject.Singleton;

import flow.path.Path;
import mortar.ViewPresenter;

@Layout(R.layout.layout_item_detail)
@WithModule(ItemDetailScreen.Module.class)
public class ItemDetailScreen extends Path {

    @dagger.Module(injects = ItemDetailView.class, addsTo = ActivityModule.class)
    public class Module {
    }

    @Singleton
    public static class Presenter extends ViewPresenter<ItemDetailView>
                        implements ForceCompletionListener{

        App mApp = new InvoiceApp();
        AddItem mAddItem = new AddItem();

        @Inject
        public Presenter() {}

        public void createItem(){

            readInputValues();

            try {

                mApp.create(ForceRequestObject.class, mAddItem, this);

            } catch (FrameworkException e) {
                e.printStackTrace();
            }

        }

        private void readInputValues() {

            mAddItem.add(Constants.ITEMSCHEMA.NAME, getView().getItemName());
            mAddItem.add(Constants.ITEMSCHEMA.DESCRIPTION, getView().getItemDescription());
            mAddItem.add(Constants.ITEMSCHEMA.RATE, getView().getItemRate());
            mAddItem.add(Constants.ITEMSCHEMA.SKU, getView().getItemSKU());
            mAddItem.add(Constants.ITEMSCHEMA.STATUS, getView().getItemStatus());
            mAddItem.add(Constants.ITEMSCHEMA.IMAGE_URL, Constants.SALESFORCE.EMPTY_STRING);

        }

        @Override
        public void onSuccess(JSONObject result) {

        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }


    }

}
