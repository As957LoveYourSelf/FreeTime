package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface FaceSignService {
    @POST()
    Flowable<ResponseBean<Object>> sign(byte[] faceImg);
}
