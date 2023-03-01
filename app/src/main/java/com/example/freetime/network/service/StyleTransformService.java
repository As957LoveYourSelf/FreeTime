package com.example.freetime.network.service;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface StyleTransformService {
    @POST
    Flowable<Object> transform(byte[] imgBytes, byte[] styleImg, String type);
}
