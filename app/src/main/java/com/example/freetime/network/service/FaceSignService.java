package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FaceSignService {
    @POST("faceSign")
    Flowable<ResponseBean<Map<String, Object>>> sign(@Body Map<String, Object> data);
}
