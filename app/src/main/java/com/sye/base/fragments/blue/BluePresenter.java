package com.sye.base.fragments.blue;

import com.sye.base.R;
import com.sye.base.network.RequestBuilder;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BluePresenter implements BlueContract.Presenter {

    private BlueContract.View view;
    private BlueServices service;

    BluePresenter(BlueContract.View view) {
        this.view = view;
    }

    @Override
    public void create() {
        RequestBuilder<BlueServices> builder = new RequestBuilder<>();
        service = builder.createRequest(BlueServices.class);
        //Init database, prepare rest, etc.
    }

    @Override
    public void destroy() {
        //Close database
    }

    @Override
    public void fetchData() {
        view.progress(true);

        service.getUsers("")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BlueObject>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(R.string.snack_error_connection);
                    }

                    @Override
                    public void onNext(List<BlueObject> listResponse) {
                        view.showSuccess(R.string.dialog_message_success_sent);
                        view.progress(false);
                    }
                });


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
