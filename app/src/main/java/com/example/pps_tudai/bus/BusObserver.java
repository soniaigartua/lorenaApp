package com.example.pps_tudai.bus;

import io.reactivex.functions.Consumer;

public abstract class BusObserver<T> implements Consumer<Object> {
    private Class<T> clazz;

    public BusObserver(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected BusObserver() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public void accept(Object value) throws Exception {
        if (value.getClass() == clazz) {
            onEvent((T) value);
        }
    }

    public abstract void onEvent(T value);
}

