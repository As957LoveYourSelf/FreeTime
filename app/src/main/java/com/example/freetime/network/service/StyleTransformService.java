package com.example.freetime.network.service;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StyleTransformService {
    @POST("defaultStyleTran")
    Flowable<byte[]> defaultStyleTransform(@Query("image") byte[] image,@Query("type") Integer type);

    @POST("anyStyleTran")
    Flowable<byte[]> anyStyleTransform(@Query("context") byte[] context,@Query("style") byte[] style);

}
