package com.example.freetime.network.service;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface StyleTransformService {
    @POST("defaultStyleTran")
    Flowable<byte[]> defaultStyleTransform(byte[] image, Integer type);

    @POST("anyStyleTran")
    Flowable<byte[]> anyStyleTransform(byte[] context, byte[] style);

}
