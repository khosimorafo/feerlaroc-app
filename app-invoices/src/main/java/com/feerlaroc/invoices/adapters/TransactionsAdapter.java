package com.feerlaroc.invoices.adapters;

import java.util.Map;

/**
 * Created by root on 2016/02/28.
 */
public class TransactionsAdapter extends ZohoRecyclerViewAdapter<TransactionHolder>{

    TransactionHolder.SelectedItemListener mListener;

    /**
     * @param key             This is the entity's API key (e.g. items, invoices, customers etc)
     * @param id              This is the customer_id to filter by
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     */
    public TransactionsAdapter(String key, String id, int modelLayout, Class viewHolderClass) {
        super(key, id, modelLayout, viewHolderClass);
    }

    /**
     * @param key             This is the entity's API key (e.g. items, invoices, customers etc)
     * @param id              This is the customer_id to filter by
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     */
    public TransactionsAdapter(String key, String id, int modelLayout, Class viewHolderClass,
                               TransactionHolder.SelectedItemListener listener) {
        super(key, id, modelLayout, viewHolderClass);
        mListener = listener;
    }


    @Override
    protected void populateViewHolder(TransactionHolder viewHolder, Map<String, Object> model, int position) {

    }
}
