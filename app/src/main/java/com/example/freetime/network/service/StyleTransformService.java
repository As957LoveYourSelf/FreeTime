package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StyleTransformService {
    @POST("defaultStyleTran")
    Flowable<ResponseBean<String>> defaultStyleTransform(@Body Map<String, Object> map);

    @POST("anyStyleTran")
    Flowable<ResponseBean<byte[]>> anyStyleTransform(@Query("context") byte[] context,@Query("style") byte[] style);

}
