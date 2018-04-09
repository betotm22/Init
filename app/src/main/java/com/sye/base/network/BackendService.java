package com.sye.base.network;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Response;


public class BackendService extends AsyncTask<Serializable, Void, RestEvent> {

    private ServiceNotifier callback;
    private RestApi service;
    private Serializable request;

    public BackendService(ServiceNotifier callback) {
        this.callback = callback;
        service = BaseClient.getApiService(RestApi.class);
    }

    public BackendService(ServiceNotifier callback, Serializable request) {
        this.callback = callback;
        this.request = request;
        service = BaseClient.getApiService(RestApi.class);
    }

    @Override
    protected RestEvent doInBackground(Serializable... params) {

        try {
            Response<? extends GenericResponse> response = handleRequest((String) params[0]);
            if (response != null) {
                return new RestEvent(response.body(), response.isSuccessful(), response.code(), response.message());
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(RestEvent restEvent) {
        super.onPostExecute(restEvent);
        if (restEvent != null) {
            callback.onSuccess(restEvent);
        }
    }

    private Response<? extends GenericResponse>
    handleRequest(String path) throws IOException {

        switch (path) {

            case Endpoint.SN_SERVICE:
                return service.fetch(path).execute();

            default: return null;
        }
    }
}
