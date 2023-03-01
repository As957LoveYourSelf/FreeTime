package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface UserManageService {
    @POST("userManagePage")
    Flowable<BaseBean<Map<String,Object>>> changeInfo(@QueryMap Map<String, Object> data);

    @POST("userManagePage")
    Flowable<BaseBean<Map<String,Object>>> changePsd(@QueryMap Map<String, String> data);

}
