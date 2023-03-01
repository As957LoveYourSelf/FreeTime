package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface FaceImportService {
    @POST()
    Flowable<BaseBean<Object>> importFace(byte[] faceImg);
}
