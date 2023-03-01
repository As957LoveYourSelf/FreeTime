package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CourseTableService {
    @GET("course/getCourseTableBySno")
    Flowable<BaseBean<Map<String, Object>>> getTableBySno(String sno);
    @GET("course/getCourseTableByTno")
    Flowable<BaseBean<Map<String, Object>>> getTableByTno(String tno);
}
