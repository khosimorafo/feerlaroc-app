package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.schema.helper.Constants;

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
     * @param id              This is the customer_id to filter by
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     */
    public TransactionsAdapter(String id, int modelLayout, Class viewHolderClass,
                               TransactionHolder.SelectedItemListener listener) {
        super(Constants.ZOHO.INVOICES, id, modelLayout, viewHolderClass);
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(TransactionHolder viewHolder, int position) {

        super.onBindViewHolder(viewHolder, position);
        viewHolder.setListener(mListener);

    }


    @Override
    protected void populateViewHolder(TransactionHolder viewHolder, Map<String, Object> model, int position) {

        viewHolder.textInvoiceDescription.setText(model.get("reference_number").toString());
        viewHolder.textDateOfPayment.setText(model.get("last_payment_date").toString());
        viewHolder.textAmountOutstanding.setText(model.get("balance").toString());
        viewHolder.textInvoiceAmount.setText(model.get("total").toString());

        Double paid = (Double) model.get("total") - (Double) model.get("balance");
        viewHolder.textAmountPaid.setText(paid.toString());

    }
}
