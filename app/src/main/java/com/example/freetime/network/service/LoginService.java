package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface LoginService {
    @GET("loginPage/applogin")
    Flowable<ResponseBean<Map<String,Object>>> post(@QueryMap Map<String, String> loginInfo);

    @POST("loginPage/loginout")
    void loginout(@Query("uid") String uid);
}
