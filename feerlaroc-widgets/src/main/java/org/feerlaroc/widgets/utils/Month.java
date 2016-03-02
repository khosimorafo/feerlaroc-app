package org.feerlaroc.widgets.utils;

/**
 * Created by root on 2016/03/01.
 */
public class Month {

    public String mName;
    public String mYear;
    public String mShortName;
    public boolean mPaid;

    public Month(String name, String year, String shortname, boolean paid){
        mName = name; mYear = year; mShortName = shortname; mPaid = paid;
    }

    public String getStatus(){
        if(mPaid){
            return "Paid";
        }
        else{
            return "Unpaid";
        }
    }

    public int getMonthNumber() {
        return 8;
    }
}
