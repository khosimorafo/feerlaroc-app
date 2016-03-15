package com.feerlaroc.zohos.schema.helper;

/**
 * Created by root on 2016/02/18.
 */
public class Constants {

    public static final class HTTP {

        public static final String FRIENDS_URL = "https://dl.dropboxusercontent.com/u/57707756/";

    }

    public static final class ZOHO {

        public static final String BASE_URL = "https://invoice.zoho.com/";

        public static final String AUTHTOKEN = "26977862edbdfe8dc4b6d1c3f0d545a6";
        public static final String ORGANIZATION_ID = "163411778";

        public static final String API = "api";
        public static final String VERSION = "v3";

        public static final String CONTACTS = "contacts";
        public static final String INVOICES = "invoices";
        public static final String ITEMS = "items";
        public static final String CUSTOMERPAYMENTS = "customerpayments";

        public static final String CUSTOMER_ID = "customer_id";
        public static final String INVOICE_ID = "invoice_id";
        public static final String CONTACT_ID = "contact_id";
        public static final String ESTIMATE_ID = "estimate_id";
        public static final String PAYMENT_ID = "payment_id";
        public static final String ITEM_ID = "item_id";

        public static final String GENERIC_AMOUNT = "amount";
        public static final String APPLIED_AMOUNT = "amount_applied";


    }

    public static final class STATUS {

        public static final String PAID = "paid";
        public static final String UNPAID = "unpaid";
        public static final String DRAFT = "draft";
        public static final String PARTIALLYPAID = "partial";
        public static final String SENT = "sent";
        public static final String OVERDUE = "overdue";
        public static final String VOID = "void";

    }



}
