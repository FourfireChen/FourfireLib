package com.fourfire.fourfirelib;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

class TestConverterFactory extends Converter.Factory {
    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {

        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    public static class TestConverter implements Converter<{

    }
}
