package com.example.freetime.network.service;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;

public interface WordDistinguishService {
    @POST()
    Flowable<Object> distinguish(byte[] imgBytes);
}
