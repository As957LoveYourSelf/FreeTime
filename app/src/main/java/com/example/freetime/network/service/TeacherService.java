package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeacherService extends UserManageService{
    @GET("teacherManagePage/getTeacherDetail")
    Flowable<BaseBean<Map<String,Object>>> getInfo(@Query("tno") String tno);
}
