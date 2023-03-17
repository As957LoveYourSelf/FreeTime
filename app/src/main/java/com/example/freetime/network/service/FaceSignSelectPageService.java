package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface FaceSignSelectPageService {
    @POST("getsignclasses")
    Flowable<ResponseBean<List<Object>>> getClasses(@Query("uid") String uid);
}
