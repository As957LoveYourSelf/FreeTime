package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FaceSignService {
    @POST("faceSign")
    Flowable<ResponseBean<Map<String, Object>>> sign(@Body Map<String, Object> data);
    @POST("getSignDetail")
    Flowable<ResponseBean<Map<String, Object>>> getDetail(@Query("classname") String classname);
    @POST("setSign")
    Flowable<ResponseBean<Object>> setSign(@Query("uid") String uid, @Query("state") Number state);
    @POST("endSign")
    Flowable<ResponseBean<Object>> endSign(@Query("classname") String classname);

}
