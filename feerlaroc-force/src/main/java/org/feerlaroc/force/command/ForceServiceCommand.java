package org.feerlaroc.force.command;

import com.feerlaroc.core.Command;
import com.salesforce.androidsdk.rest.RestClient;
import com.salesforce.androidsdk.rest.RestRequest;
import com.salesforce.androidsdk.rest.RestResponse;

import org.feerlaroc.force.listener.ForceCompletionListener;
import org.feerlaroc.force.model.ForceEntity;
import org.feerlaroc.force.service.ForceApi;
import org.json.JSONException;

import java.io.IOException;


public abstract class ForceServiceCommand<T extends ForceEntity> extends Command implements ForceCommand {

    RestClient mRestClient;

    abstract RestRequest getRestRequest();

    @Override
    public <T> void add(T t, final ForceCompletionListener listener) {

        ForceEntity entity = (ForceEntity) t;
        mRestClient = ForceApi.getInstance().get();

        mRestClient.sendAsync(entity.getRequest(), new RestClient.AsyncRequestCallback() {
            @Override
            public void onSuccess(RestRequest request, RestResponse response) {

                try {

                    listener.onSuccess(response.asJSONObject());

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Exception exception) {

                listener.onError();

            }
        });


    }

    @Override
    public <T> void update(T t, ForceCompletionListener listener) {

    }

    @Override
    public <T> void remove(T t, ForceCompletionListener listener) {

    }

    @Override
    public <T> void remove(String key, String id, ForceCompletionListener listener) {

    }

    @Override
    public <T> void get(T t, ForceCompletionListener listener) {

    }

    @Override
    public Class getServiceClass() {
        return null;
    }
}
