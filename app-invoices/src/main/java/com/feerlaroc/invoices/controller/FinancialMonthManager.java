package com.feerlaroc.invoices.controller;

import com.feerlaroc.invoices.model.Month;
import com.feerlaroc.invoices.quiry.CustomerTransactionsArray;

import org.feerlaroc.utils.datetime.utils.FeerlarocDateUtils;
import org.feerlaroc.widgets.rangebar.PinData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 2016/03/09.
 */
public class FinancialMonthManager {

    static List<PinData> mMonths;

    public static List<PinData> getMonths(CustomerTransactionsArray args) {

        List<PinData> months = new ArrayList<>();

        for (int i=0; i < args.getArraySize(); i++){

            String dateInString = args.getItem(i)
                    .get("due_date").toString();
            Date date = FeerlarocDateUtils.parseTimestamp(dateInString);

            Month feerlarocMonth = new Month(date);
            feerlarocMonth.addFinancialObject(args.getKey(), args.getItem(i));

            months.add(feerlarocMonth);

        }
        Collections.sort(months);

        return months;
    }

    public static List<PinData> getMonths(List <Date> dateList){

        mMonths = new ArrayList<>();

        for(Date date : dateList) {
            Month feerlarocMonth = new Month(date);
            mMonths.add(feerlarocMonth);
        }

        Collections.sort(mMonths );

        return mMonths;

    }

}
