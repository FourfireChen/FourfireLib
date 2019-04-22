package com.fourfire.fourfirelib;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface RetrofitTest {
    @GET("/wxarticle/chapters/json")
    Call<ResponseBody> getIt();
}
