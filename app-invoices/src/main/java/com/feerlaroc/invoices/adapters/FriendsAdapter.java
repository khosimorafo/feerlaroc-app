package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.response.Friend;

/**
 * Created by root on 2016/02/25.
 */
public class FriendsAdapter  extends ZohoRecyclerViewAdapter<Friend, FriendHolder> {
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public FriendsAdapter(Class<Friend> modelClass, int modelLayout, Class<FriendHolder> viewHolderClass) {
        super(modelClass, modelLayout, viewHolderClass);
    }

    @Override
    protected void populateViewHolder(FriendHolder viewHolder, Friend model, int position) {

    }
}
