package com.sye.base.util;


import java.util.List;

public interface Callback {

    void sendBack();
    void sendBack(String message);
    void sendBack(int value);
    void sendBack(boolean flag);
    void sendBack(Object object);
    void sendBack(List<Object> list);
    void sendBack(Object ... objects);
}
