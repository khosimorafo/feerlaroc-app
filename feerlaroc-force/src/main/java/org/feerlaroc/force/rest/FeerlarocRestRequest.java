package org.feerlaroc.force.rest;

import com.salesforce.androidsdk.rest.RestRequest;

import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by root on 2016/03/17.
 */
public class FeerlarocRestRequest extends RestRequest {

    public FeerlarocRestRequest(RestMethod method, String path, HttpEntity requestEntity) {
        super(method, path, requestEntity);
    }

    public FeerlarocRestRequest(RestMethod method, String path, HttpEntity requestEntity, Map<String, String> additionalHttpHeaders) {
        super(method, path, requestEntity, additionalHttpHeaders);
    }

    /**
     * Enumeration for all REST API actions.
     */
    private enum RestAction {

        FEERCREATE("/services/apexrest/%s/%s");

        private final String pathTemplate;

        RestAction(String uriTemplate) {
            this.pathTemplate = uriTemplate;
        }

        public String getPath(Object... args) {
            return String.format(pathTemplate, args);
        }
    }

    /**
     * Request to create a record.
     * See http://www.salesforce.com/us/developer/docs/api_rest/Content/resources_sobject_retrieve.htm
     *
     * @param apiVersion
     * @param objectType
     * @param fields
     * @return a RestRequest
     * @throws IOException
     * @throws UnsupportedEncodingException
     */

    public static FeerlarocRestRequest getRequestForCreate(String apiVersion, String objectType, Map<String, Object> fields) throws IOException  {
        HttpEntity fieldsData = prepareFieldsData(fields);
        return new FeerlarocRestRequest(RestMethod.POST, RestAction.FEERCREATE.getPath("v1", objectType), fieldsData);
    }

}
