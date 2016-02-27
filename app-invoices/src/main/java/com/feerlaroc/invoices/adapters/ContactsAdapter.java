package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.schema.pojo.Contact;

/**
 * Created by root on 2016/02/23.
 */

public class ContactsAdapter extends ZohoRecyclerViewAdapter<Contact, ContactHolder> {

    ContactHolder.SelectedItemListener mListener;
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public ContactsAdapter(Class modelClass, int modelLayout, Class viewHolderClass) {
        super(modelClass, modelLayout, viewHolderClass);
    }

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public ContactsAdapter(Class modelClass, int modelLayout, Class viewHolderClass,
                           ContactHolder.SelectedItemListener listener) {
        super(modelClass, modelLayout, viewHolderClass);
        mListener = listener;
    }

    @Override
    public void onBindViewHolder(ContactHolder viewHolder, int position) {
        super.onBindViewHolder(viewHolder, position);
        viewHolder.setListener(mListener);

    }

    @Override
    protected void populateViewHolder(ContactHolder viewHolder, Contact contact, int position) {

        viewHolder.textDriverName.setText(contact.getContactName());
        viewHolder.textOutstandingAmount.setText(contact.getOutstandingReceivableAmount().toString());
        //viewHolder.circleImageDriver.setImageDrawable();

    }

}
