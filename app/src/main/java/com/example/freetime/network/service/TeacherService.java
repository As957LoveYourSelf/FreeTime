package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeacherService extends UserManageService{
    @GET("teacherManagePage/getTeacherDetail")
    Flowable<ResponseBean<Map<String,Object>>> getInfo(@Query("tno") String tno);
}
