package com.sye.base.network;

import android.os.AsyncTask;

import com.sye.base.MyApplication;

import java.io.IOException;
import java.io.Serializable;

import retrofit2.Response;


public class BackendService<T> extends AsyncTask<Serializable, Void, RestEvent> {

    private ServiceNotifier callback;
    private String auth;

    public BackendService(ServiceNotifier callback) {
        this.callback = callback;
        auth = MyApplication.auth();
    }

    @Override
    protected RestEvent doInBackground(Serializable... params) {

        RestApi<T> service = new ServiceBuilder<RestApi<T>>().create(RestApi.class);
        try {
            Response response =  service.fetch(auth, (String) params[0]).execute();
            T body = (T) response.body();
            callback.onSuccess(new RestEvent<>(body, response.isSuccessful(), response.code(), response.message()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

//
//    /**
//     * Use this method to make every requests to a service configured in this class.
//     */
//    public static void createRequest(Serializable params, String type) {
//        Intent intent = new Intent(MyApplication.app(), BackendService.class);
//        intent.putExtra(BackendService.KEY_TYPE, type);
//        intent.putExtra(BackendService.KEY_PARAMS, params);
//        if (isOnline(MyApplication.app()))
//            MyApplication.app().startService(intent);
//    }

//    /**
//     * To make a new request to a web service, add the service to {@link RestApi}, then,
//     * add the case in {@link BackendService#handleServices(String, Serializable)} and create its own
//     * method to get a Respose<T>.
//     * This will help to make Unit Test of web services.
//     * Don't forget to subscribe the class that is calling the service to EventBus.
//     */
//    private void handleServices(String type, Serializable params) throws IOException {
//
//        RestEvent restEvent;
//
//        switch (type) {
//            case Endpoint.SN_SERVICE: //region
//                Response<BlueObject> response = service.getService().execute();
//                restEvent = new RestEvent(response.body(), response.isSuccessful(), response.code(),
//                        response.message(), type);
//                break; //endregion
//
//            case Endpoint.SN_LISTS_RESPONSE: //region
//                BlueObject request = (BlueObject) params;
//                Response<List<BlueObject>> statesResponse = service.getUsers(auth).execute();
//
//                List<Serializable> serializedStatesResponse = new ArrayList<>();
//                serializedStatesResponse.addAll(statesResponse.body());
//
//                restEvent = new RestEvent(serializedStatesResponse, statesResponse.isSuccessful(),
//                        statesResponse.code(), statesResponse.message(), type);
//                break; //endregion
//
//            case Endpoint.SN_VOID_RESPONSE: //region
//                Response<Void> deleteAddress = service.deleteService("").execute();
//                restEvent = new RestEvent(deleteAddress.isSuccessful(), deleteAddress.code(),
//                        deleteAddress.message(), type);
//                break; //endregion
//
//            default:
//                restEvent = null;
//        }
//
//        EventBus.getDefault().post(restEvent);
//    }

}
