package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface LoginService {

    @GET("loginPage/login")
    Flowable<BaseBean<Map<String,Object>>> post(@QueryMap Map<String, String> loginInfo);
}
