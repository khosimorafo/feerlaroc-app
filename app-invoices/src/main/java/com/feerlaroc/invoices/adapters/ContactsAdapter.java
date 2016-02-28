package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.schema.helper.Constants;

import java.util.Map;

/**
 * Created by root on 2016/02/23.
 */

public class ContactsAdapter extends ZohoRecyclerViewAdapter<ContactHolder> {

    ContactHolder.SelectedItemListener mListener;
    /**
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public ContactsAdapter(int modelLayout, Class viewHolderClass) {
        super(Constants.ZOHO.CONTACTS, modelLayout, viewHolderClass);
    }

    /**
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public ContactsAdapter(int modelLayout, Class viewHolderClass,
                           ContactHolder.SelectedItemListener listener) {
        super(Constants.ZOHO.CONTACTS, modelLayout, viewHolderClass);
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(ContactHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        viewHolder.setListener(mListener);

    }

    @Override
    protected void populateViewHolder(ContactHolder viewHolder, Map<String, Object> contactMap, int position) {

        viewHolder.textContactName.setText(contactMap.get("contact_name").toString());
        viewHolder.textOutstandingAmount.setText(contactMap.get("outstanding_receivable_amount").toString());

    }

}
