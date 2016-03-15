package com.feerlaroc.invoices.model;

import com.feerlaroc.invoices.R;
import com.feerlaroc.invoices.utils.ColorUtils;
import com.feerlaroc.zohos.schema.helper.Constants;

import org.feerlaroc.utils.datetime.FeerlarocMonth;
import org.feerlaroc.widgets.rangebar.PinData;
import org.feerlaroc.widgets.rangebar.PinView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 2016/03/01.
 */
public class Month extends FeerlarocMonth implements PinData{

    PinView.OnClickListener mListener;

    Map<String, Object> mFinancialObjects = new HashMap<>();

    public Month(Date date) {
        super(date);
    }

    public Month(Calendar calendar) {
        super(calendar);
    }


    @Override
    public String getXValue() {
        return getMonthStringShort();
    }

    @Override
    public String getTag() {
        return getTagValue();
    }

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }

    @Override
    public int getColor() {

        Map<String, Object> actualObject = new HashMap<>();
        List<String> status = new ArrayList<>();

        for (Map.Entry<String, Object> entry : mFinancialObjects.entrySet()) {

            if(entry.getKey().equals(Constants.ZOHO.INVOICES)) {

                actualObject = (Map<String, Object>) entry.getValue();
                status.add(actualObject.get("status").toString());

            }

        }

        for (String s : status){

            switch (s) {

                case Constants.STATUS.OVERDUE : return ColorUtils.OVERDUE;
                case Constants.STATUS.UNPAID : return ColorUtils.UNPAID;
                case Constants.STATUS.PAID : return ColorUtils.PAID;
                case Constants.STATUS.PARTIALLYPAID : return ColorUtils.PARTIALLYPAID;
                case Constants.STATUS.DRAFT : return ColorUtils.DRAFT;
                case Constants.STATUS.SENT : return ColorUtils.SENT;
                case Constants.STATUS.VOID : return ColorUtils.VOID;

            }

        }

        return 0;
    }

    @Override
    public int ID() {
        return R.id.pin_1;
    }

    public void setPinListener(PinView.OnClickListener listener) {
        mListener = listener;
    }


    public void addFinancialObject(String key, Map<String, Object> financialObject){

        mFinancialObjects.put(key, financialObject);
        //TODO: Implement a case statement
        mTag = financialObject.get("invoice_id").toString();
        getColor();

    }

    @Override
    public int compareTo(Object another) {

        Month month = (Month) another;
        return this.mDate.before(month.mDate) ? -1 : 1;

    }
}
