package com.feerlaroc.invoices.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.feerlaroc.invoices.InvoiceApplication;
import com.feerlaroc.invoices.R;

/**
 * Created by root on 2016/03/09.
 */
public class ColorUtils {

    static Context mContext = InvoiceApplication.getInstance().getBaseContext();

    public static final int PAID = ContextCompat.getColor(mContext, R.color.color_paid);
    public static final int UNPAID = ContextCompat.getColor(mContext, R.color.color_unpaid);
    public static final int DRAFT = ContextCompat.getColor(mContext, R.color.color_draft);
    public static final int OVERDUE = ContextCompat.getColor(mContext, R.color.color_overdue);
    public static final int PARTIALLYPAID = ContextCompat.getColor(mContext, R.color.color_partially_paid);
    public static final int SENT = ContextCompat.getColor(mContext, R.color.color_sent);
    public static final int VOID = ContextCompat.getColor(mContext, R.color.color_void);

}
