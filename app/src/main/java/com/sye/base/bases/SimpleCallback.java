package com.sye.base.bases;


import java.util.List;

public interface SimpleCallback {

    void sendBack(List<Object> list);
    void sendBack(Object ... objects);
}
