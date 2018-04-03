package com.sye.base.fragments.blue;

import com.sye.base.network.BackendService;
import com.sye.base.network.SN;

public class BluePresenter implements BlueContract.Presenter {

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
        BackendService.createRequest(null, SN.SN_LISTS_RESPONSE);
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
