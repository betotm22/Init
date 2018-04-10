package com.sye.base.fragments.blue;


import android.util.Log;

import com.sye.base.network.RequestManager;
import com.sye.base.network.Endpoint;
import com.sye.base.network.RestEvent;
import com.sye.base.network.ServiceNotifier;

public class BluePresenter implements BlueContract.Presenter {

    private BlueContract.View view;
    private RequestManager manager;

    BluePresenter(BlueContract.View view) {
        this.view = view;
    }

    @Override
    public void create() {
        manager = RequestManager.build();
        //Init database, prepare rest, etc.
    }

    @Override
    public void destroy() {
        manager.release();
        //Close database
    }

    @Override
    public void fetchData() {
        view.progress(true);
        manager.setResponseListener(result -> {

            BlueObject obj = (BlueObject) result.getResponse();
            Log.i("RESULT", obj.getConsumo());

        });
        manager.execute(Endpoint.EP_SERVICE);
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
}
