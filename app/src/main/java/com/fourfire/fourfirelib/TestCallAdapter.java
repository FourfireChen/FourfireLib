package com.fourfire.fourfirelib;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;

public class TestCallAdapter implements CallAdapter<ResponseBody, String> {
    @Override public Type responseType() {
        return String.class;
    }

    @Override public String adapt(Call<ResponseBody> call) {
        try {
            return call.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
