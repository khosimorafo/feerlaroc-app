package com.feerlaroc.invoices.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2016/03/18.
 */
public class ForceEntityArray {

    List<Map<String, Object>> mSnapshots = new ArrayList<>();
    OnDataChangedListener mListener;

    String mKey;


    public int getArraySize(){
        return mSnapshots.size();
    }

    public Map<String, Object> getItem(int index) {
        return mSnapshots.get(index);
    }

    public String getKey(){
        return mKey;
    }

    public void setOnChangedListener(OnDataChangedListener listener) {
        mListener = listener;
    }

    protected void notifyChangedListeners() {
        if (mListener != null) {
            mListener.onChanged();
        }
    }

    public interface OnDataChangedListener {
        void onChanged();
    }


}
