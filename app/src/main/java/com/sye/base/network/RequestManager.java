package com.sye.base.network;

import android.os.AsyncTask;

import com.sye.base.fragments.blue.BlueObject;
import com.sye.base.network.bases.GenericResponse;
import com.sye.base.network.bases.RestEvent;
import com.sye.base.network.bases.ServiceNotifier;
import com.sye.base.network.config.BaseClient;
import com.sye.base.util.Set;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * RequestManager is an AsyncTask that makes the requests to services and handle the responses and
 * send them back through a callback.
 */
public class RequestManager extends AsyncTask<Serializable, Void, RestEvent> {

    //region DON'T TOUCH IT

    //region VARIABLES

    /**
     * Object with the services to request.
     */
    private RestApi service;

    /**
     * Callback that returns the responses from the services requested to the class that
     * make the calls. The callback just contains a single method that sends a {@link RestEvent}.
     */
    private ServiceNotifier callback;

    /**
     * Request body if the service if needed by the service.
     */
    private Serializable request;

    /**
     * Variable that indicates the {@link #doInBackground(Serializable...)} method how to handle
     * the response: True if the response has no body
     */
    private boolean voidResponse = false;

    /**
     * Variable that indicates the {@link #doInBackground(Serializable...)} method how to handle
     * the response: True if the response is an Array
     */
    private boolean listResponse = false;

    /**
     * Instance of the class itself.
     */
    private static RequestManager instance;

    //endregion

    //region METHODS

    /**
     * Constructor method that initializes the {@link RestApi} object
     */
    private RequestManager() {
        service = BaseClient.getApiService(RestApi.class);
    }

    /**
     * Initializes of the class itself, avoid multiple instance of the same object, so the user can
     * make custom requests whit a single instance
     * @return Instance of {@link RequestManager}
     */
    public static RequestManager build() {
        if (instance == null) {
            instance = new RequestManager();
        }
        return instance;
    }

    /**
     * Initializes the callback which will return the responses
     * @param callback Object that will receive the responses
     * @return Instance of {@link RequestManager}
     */
    public RequestManager setResponseListener(ServiceNotifier callback) {
        instance.callback = callback;
        return instance;
    }

    /**
     * Initializes the request body of a service if needed
     * @param request The request body that will be send
     * @return Instance of {@link RequestManager}
     */
    public RequestManager addRequestBody(Serializable request) {
        instance.request = request;
        return instance;
    }

    /**
     * Initializes the variable which indicates if the services has a Void response
     * @param voidResponse Flag
     * @return Instance of {@link RequestManager}
     */
    public RequestManager hasVoidResponse(boolean voidResponse) {
        instance.voidResponse = voidResponse;
        return instance;
    }

    /**
     * Initializes the variable which indicates if the services has an Array response
     * @param listResponse Flag
     * @return Instance of {@link RequestManager}
     */
    public RequestManager hasListResponse(boolean listResponse) {
        instance.listResponse = listResponse;
        return instance;
    }

    /**
     * Makes the instance of the class itself in order to make a new clear instance of the class
     */
    public void release() {
        instance = null;
    }

    //endregion

    //region REQUEST

    @Override
    protected RestEvent doInBackground(Serializable... params) {

        try {

            Response<? extends GenericResponse> response = handleObjectRequest((String) params[0]);
            if (response != null)
                return new RestEvent(response.body(), response.isSuccessful(), response.code(), response.message());

            if (listResponse) {
                return handleListRequest((String) params[0]);
            }

            if (voidResponse) {
                Response<Void> voidResponse = handleVoidRequest((String) params[0]);
                if (voidResponse != null)
                    return new RestEvent(voidResponse.isSuccessful(), voidResponse.code(), voidResponse.message());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RestEvent(false, Set.CODE_SERVER_ERROR, null);
    }

    @Override
    protected void onPostExecute(RestEvent restEvent) {
        super.onPostExecute(restEvent);
        if (restEvent != null && callback != null) {
            callback.onResponse(restEvent);
        }
    }

    //endregion

    //endregion

    /**
     * This method handle the responses when they are objects, user should know if the type of
     * response that the service will return.
     * @param path Endpoint set from the request of execute the background service (as param of the
     *             the thread)
     * @return It will return the response from service and the method
     *             {@link #doInBackground(Serializable...)} will setup the {@link RestEvent} object.
     * @throws IOException Required by default
     */
    private Response<? extends GenericResponse>
    handleObjectRequest(String path) throws IOException {

        switch (path) {

            case Endpoint.EP_SERVICE: //TODO: sample code
                return service.fetch(path).execute();

            default:
                return null;
        }
    }

    /**
     * This method handle the responses when they are lists, user should know if the type of
     * response that the service will return.
     * @param path Endpoint set from the request of execute the background service (as param of the
     *             the thread)
     * @return It will return a {@link RestEvent} object with the list already set; the response
     *             must be casted to an Array of {@link GenericResponse} in order to save it on a
     *             {@link RestEvent} object.
     * @throws IOException Required by default
     */
    private RestEvent handleListRequest(String path) throws IOException {

        switch (path) {

            case Endpoint.EP_USER: //TODO: sample code
                Response<List<BlueObject>> response = service.fetchList(path).execute();
                ArrayList<GenericResponse> genericResponses = new ArrayList<>(response.body());
                return new RestEvent(genericResponses, response.isSuccessful(), response.code(), response.message());

            default:
                return null;
        }
    }

    /**
     * This method handle the responses when the services doesn't has a response body (Void response)
     * @param path Endpoint set from the request of execute the background service (as param of the
     *             the thread)
     * @return It will return a {@link RestEvent} object without response body, user should know that
     *             there's not a response in order to avoid NullPointerExceptions.
     * @throws IOException Required by default
     */
    private Response<Void>
    handleVoidRequest(String path) throws IOException {

        switch (path) {

            case "void_endpoint": //TODO: sample code
                return service.deleteService(path).execute();

            default:
                return null;
        }
    }
}
