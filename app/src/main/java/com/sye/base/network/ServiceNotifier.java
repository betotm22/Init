package com.sye.base.network;

public interface ServiceNotifier {

    void onSuccess(RestEvent result);
    void onFailed(RestEvent result);
}
