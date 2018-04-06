package com.sye.base.fragments.blue;

import com.sye.base.bases.BaseContractPresenter;
import com.sye.base.bases.BaseContractView;

import java.util.List;

public interface BlueContract {

    interface Presenter extends BaseContractPresenter {

    }

    interface View extends BaseContractView {
        void showData(List<BlueObject> list);
    }
}
