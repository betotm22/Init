package com.sye.base.util;

public interface BaseContractView {

    void showSuccess(int message);
    void showError(int message);

    void progress(boolean show);

}
