package org.feerlaroc.force.schema.helper;

/**
 * Created by root on 2016/03/17.
 */
public class Constants {

    public static final class SALESFORCE {

        public static final String EMPTY_STRING = "";


        public static final String API = "api";
        public static final String VERSION = "v3";

        public static final String CONTACTS = "contacts";
        public static final String INVOICES = "invoices";
        public static final String ITEMS = "items";
        public static final String CUSTOMERPAYMENTS = "customerpayments";

        public static final String CONTACT = "contact";
        public static final String INVOICE = "invoice";
        public static final String ITEM = "item";
        public static final String CUSTOMERPAYMENT = "customerpayment";

        public static final String ENTRY = "entry";

        public static final String CUSTOMER_ID = "customer_id";
        public static final String INVOICE_ID = "invoice_id";
        public static final String CONTACT_ID = "contact_id";
        public static final String ESTIMATE_ID = "estimate_id";
        public static final String PAYMENT_ID = "payment_id";
        public static final String ITEM_ID = "item_id";

        public static final String GENERIC_AMOUNT = "amount";
        public static final String APPLIED_AMOUNT = "amount_applied";


    }

    public static final class ITEMSCHEMA {

        public static final String NAME = "item_name";
        public static final String DESCRIPTION = "item_description";
        public static final String RATE = "item_rate";
        public static final String STATUS = "status";
        public static final String SKU = "sku";
        public static final String IMAGE_URL = "image_url";


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
