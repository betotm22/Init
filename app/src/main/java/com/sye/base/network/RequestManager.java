package com.sye.base.network;

import android.os.AsyncTask;

import com.sye.base.fragments.blue.BlueObject;
import com.sye.base.util.Set;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;


public class RequestManager extends AsyncTask<Serializable, Void, RestEvent> {

    private ServiceNotifier callback;
    private RestApi service;
    private Serializable request;
    private boolean voidResponse = false;
    private boolean listResponse = false;

    private static RequestManager instance;

    private RequestManager() {
        service = BaseClient.getApiService(RestApi.class);
    }

    public static RequestManager build() {
        if (instance == null) {
            instance = new RequestManager();
        }
        return instance;
    }

    public RequestManager setResponseListener(ServiceNotifier callback) {
        instance.callback = callback;
        return instance;
    }

    public RequestManager addRequestBody(Serializable request) {
        instance.request = request;
        return instance;
    }

    public RequestManager hasVoidResponse(boolean voidResponse) {
        instance.voidResponse = voidResponse;
        return instance;
    }

    public RequestManager hasListResponse(boolean listResponse) {
        instance.listResponse = listResponse;
        return instance;
    }

    public void release() {
        instance = null;
    }

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

    private Response<? extends GenericResponse>
    handleObjectRequest(String path) throws IOException {

        switch (path) {

            case Endpoint.EP_SERVICE:
                return service.fetch(path).execute();

            default:
                return null;
        }
    }

    private RestEvent handleListRequest(String path) throws IOException {

        switch (path) {

            case Endpoint.EP_USER:
                Response<List<BlueObject>> response = service.fetchList(path).execute();
                ArrayList<GenericResponse> genericResponses = new ArrayList<>(response.body());
                return new RestEvent(genericResponses, response.isSuccessful(), response.code(), response.message());

            default:
                return null;
        }
    }

    private Response<Void>
    handleVoidRequest(String path) throws IOException {

        switch (path) {

            case "void_endpoint":
                return service.deleteService(path).execute();

            default:
                return null;
        }
    }
}
