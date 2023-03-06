package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SuperResolveService {
    @POST("enhance")
    Flowable<ResponseBean<byte[]>> enhance(@Query("img") byte[] img);
}
