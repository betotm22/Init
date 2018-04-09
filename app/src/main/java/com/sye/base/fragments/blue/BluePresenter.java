package com.sye.base.fragments.blue;


import android.util.Log;

import com.sye.base.network.BackendService;
import com.sye.base.network.Endpoint;
import com.sye.base.network.RestApi;
import com.sye.base.network.RestEvent;
import com.sye.base.network.ServiceBuilder;
import com.sye.base.network.ServiceNotifier;

public class BluePresenter implements BlueContract.Presenter, ServiceNotifier {

    private BlueContract.View view;

    BluePresenter(BlueContract.View view) {
        this.view = view;
    }

    @Override
    public void create() {
        //Init database, prepare rest, etc.
    }

    @Override
    public void destroy() {
        //Close database
    }

    @Override
    public void fetchData() {
        view.progress(true);

        new BackendService(this).execute(Endpoint.SN_SERVICE);
        //Call to service, search on database, etc.
    }

    @Override
    public void save() {
        //Save in database, send to service, etc.
    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    @Override
    public void onSuccess(RestEvent result) {
        Log.i("RESULT", ((BlueObject)result.getResponse()).getConsumo());
    }

    @Override
    public void onFailed(RestEvent result) {

    }
}
