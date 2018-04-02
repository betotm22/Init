package com.sye.base.fragments.blue;

import com.sye.base.util.BaseContractPresenter;
import com.sye.base.util.BaseContractView;

public interface BlueContract {

    interface Presenter extends BaseContractPresenter {

    }

    interface View extends BaseContractView {
        void showData();
    }
}
