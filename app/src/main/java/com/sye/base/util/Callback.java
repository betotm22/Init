package com.sye.base.util;


import java.util.List;

public interface Callback {

    void sendBack(List<Object> list);
    void sendBack(Object ... objects);
}
