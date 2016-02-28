package com.feerlaroc.invoices.adapters;

import com.feerlaroc.zohos.response.Friend;

import java.util.Map;

/**
 * Created by root on 2016/02/25.
 */
public class FriendsAdapter  extends ZohoRecyclerViewAdapter< FriendHolder> {
    /**
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param
     */
    public FriendsAdapter(Class<Friend> modelClass, int modelLayout, Class<FriendHolder> viewHolderClass) {
        super(null, modelLayout, viewHolderClass);
    }

    @Override
    protected void populateViewHolder(FriendHolder viewHolder, Map<String, Object> model, int position) {

    }


}
