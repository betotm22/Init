package com.sye.base.network;

import android.app.IntentService;
import android.content.Intent;

import com.sye.base.MyApplication;
import com.sye.base.fragments.blue.BlueModel;
import com.sye.base.util.Set;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

import static com.sye.base.util.Utils.Connection.isOnline;

public class BackendService extends IntentService {

    private static final String KEY_TYPE = "serviceType";
    private static final String KEY_PARAMS = "params";
    private String auth;

    private RestApi service;

    public BackendService() {
        super("BackendService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            String serviceType = intent.getExtras().getString(KEY_TYPE);
            Serializable params = intent.getExtras().getSerializable(KEY_PARAMS);
            auth = MyApplication.auth();

            initService();

            handleServices(serviceType, params);

        } catch (IOException exception) {
            exception.printStackTrace();
            RestEvent event = new RestEvent();
            EventBus.getDefault().post(event);
        }
    }

    /**
     * Use this method to make every requests to a service configured in this class.
     */
    public static void createRequest(Serializable params, String type) {
        Intent intent = new Intent(MyApplication.app(), BackendService.class);
        intent.putExtra(BackendService.KEY_TYPE, type);
        intent.putExtra(BackendService.KEY_PARAMS, params);
        if (isOnline(MyApplication.app()))
            MyApplication.app().startService(intent);
    }

    public void initService() {
        service = BaseClient.getApiService(Set.BASE_URL);
    }

    /**
     * To make a new request to a web service, add the service to {@link RestApi}, then,
     * add the case in {@link BackendService#handleServices(String, Serializable)} and create its own
     * method to get a Respose<T>.
     * This will help to make Unit Test of web services.
     * Don't forget to subscribe the class that is calling the service to EventBus.
     */
    private void handleServices(String type, Serializable params) throws IOException {

        RestEvent restEvent;

        switch (type) {
            case SN.SN_SERVICE: //region
                Response<BlueModel> response = service.getService().execute();
                restEvent = new RestEvent(response.body(), response.isSuccessful(), response.code(),
                        response.message(), type);
                break; //endregion

            case SN.SN_LISTS_RESPONSE: //region
                BlueModel request = (BlueModel) params;
                Response<List<BlueModel>> statesResponse = service.getUsers(auth).execute();

                List<Serializable> serializedStatesResponse = new ArrayList<>();
                serializedStatesResponse.addAll(statesResponse.body());

                restEvent = new RestEvent(serializedStatesResponse, statesResponse.isSuccessful(),
                        statesResponse.code(), statesResponse.message(), type);
                break; //endregion

            case SN.SN_VOID_RESPONSE: //region
                Response<Void> deleteAddress = service.deleteService("").execute();
                restEvent = new RestEvent(deleteAddress.isSuccessful(), deleteAddress.code(),
                        deleteAddress.message(), type);
                break; //endregion

            default:
                restEvent = null;
        }

        EventBus.getDefault().post(restEvent);
        stopSelf();//Por las dudas
    }
}
