package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ColorizationService {
    @POST("colorization")
    Flowable<ResponseBean<String>> colorization(@Body Map<String, Object> data);
}
