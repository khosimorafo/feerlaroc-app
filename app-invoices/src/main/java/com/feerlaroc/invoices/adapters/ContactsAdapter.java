package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.adapter.ZohoRecyclerViewAdapter;

import rx.Observable;

/**
 * Created by root on 2016/02/23.
 */
public class ContactsAdapter extends ZohoRecyclerViewAdapter {
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param observable
     */
    public ContactsAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Observable observable) {
        super(modelClass, modelLayout, viewHolderClass, observable);
    }
}
