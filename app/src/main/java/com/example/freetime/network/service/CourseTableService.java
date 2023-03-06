package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface CourseTableService {
    @POST("course/getCourseTableBySno")
    Flowable<ResponseBean<Map<String, Object>>> getTableBySno(@Query("sno") String sno);
    @POST("course/getCourseTableByTno")
    Flowable<ResponseBean<Map<String, Object>>> getTableByTno(@Query("tno") String tno);
}
