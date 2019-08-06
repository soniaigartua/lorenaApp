package com.example.pps_tudai.data.entities.asyncTask;

public interface DbGenericQuery<T, S> {
    public T executeQuery(S parameter);
}
