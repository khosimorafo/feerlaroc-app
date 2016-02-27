package com.feerlaroc.zohos.response;

import com.feerlaroc.zohos.schema.pojo.Contact;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContactResponse extends AbstractResponse {

    public ContactResponse(){ /*Required empty bean constructor*/ }

    @SerializedName("contacts")
    List<Contact> mContacts;

    public List<Contact> getList() {
        return mContacts;
    }

}
