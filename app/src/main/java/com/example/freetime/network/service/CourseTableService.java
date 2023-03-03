package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

public interface CourseTableService {
    @GET("course/getCourseTableBySno")
    Flowable<ResponseBean<Map<String, Object>>> getTableBySno(String sno);
    @GET("course/getCourseTableByTno")
    Flowable<ResponseBean<Map<String, Object>>> getTableByTno(String tno);
}
