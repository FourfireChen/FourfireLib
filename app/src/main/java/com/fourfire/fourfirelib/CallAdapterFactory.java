package com.fourfire.fourfirelib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CallAdapterFactory extends CallAdapter.Factory {

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new TestCallAdapter();
    }
}
