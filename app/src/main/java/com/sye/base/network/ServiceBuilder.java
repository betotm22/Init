package com.sye.base.network;

public class ServiceBuilder<T> {

    private T type;

    @SuppressWarnings("unchecked")
    public T create(Class clazz) {
        return (T) BaseClient.getApiService(clazz);
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
