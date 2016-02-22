package com.feerlaroc.zohos.schema.callback;

import java.util.ArrayList;

/**
 * Created by root on 2016/02/21.
 */
public class FriendResponse {

    public FriendLocations friendLocations;

    public FriendResponse(){}

    public class FriendLocations {
        public Data data;
        public class Data{
            public ArrayList<Friend> friend = new ArrayList<>();
            public class Friend{
                public String friendName;
                public String friendType;
                public String lat;
                public String lon;
            }
        }
    }
}
