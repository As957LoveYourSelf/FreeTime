package com.example.freetime.network.service;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WordDistinguishService {
    @POST()
    Flowable<Object> distinguish(@Query("imgBytes") byte[] imgBytes);
}
