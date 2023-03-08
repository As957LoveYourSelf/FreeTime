package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface UserManageService {
    @POST("userManagePage/changInfo")
    Flowable<ResponseBean<Map<String,Object>>> changeInfo(@Query ("uid") String uid, @Body Map<String, Object> data);

    @POST("userManagePage/changePassword")
    Flowable<ResponseBean<Map<String,Object>>> changePsd(@Body Map<String, String> data);

}
