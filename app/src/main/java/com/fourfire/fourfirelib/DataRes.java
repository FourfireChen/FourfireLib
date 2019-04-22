package com.fourfire.fourfirelib;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DataRes {
    private OkHttpClient client;
    private Retrofit retrofit;

    public DataRes() {
        client = new OkHttpClient();
        // !!!converter是转换Call的泛型里的东西，而calladapter是把call转成别的东西
        retrofit =
                new Retrofit.Builder().baseUrl("https://www.wanandroid.com")
                        .addCallAdapterFactory(new CallAdapterFactory())
                        .addConverterFactory(new TestConverterFactory())
                        .build();
    }

    public void test() {
        Request request = new Request.Builder().url("http://baidu.com").get().build();
    }

    public void testRetrofit(Callback callback) {
        retrofit.create(RetrofitTest.class).getIt().enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    callback.onSuccess(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFail();
            }
        });

    }

    public interface Callback {
        void onSuccess(String content);

        void onFail();
    }
}
