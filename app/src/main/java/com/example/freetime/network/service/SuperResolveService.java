package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface SuperResolveService {
    @POST("enhance")
    Flowable<ResponseBean<byte[]>> enhance(byte[] img);
}
