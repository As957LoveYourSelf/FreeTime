package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RoomReservationService {
    @POST("classroomManage/getClassroomInfo")
    Flowable<ResponseBean<List<Object>>> getRooms(@Query("buildingName") String buildingName, @Query("isOrder") Integer isOrder, @Query("floor") Integer floor);

    @POST("classroomManage/orderClassroom")
    Flowable<ResponseBean<String>> reserve(@Query("uid") String uid, @Query("clsid") String clsid);

    @POST("classroomManage/deorderClassroom")
    Flowable<ResponseBean<String>> dereserve(@Query("uid") String uid, @Query("clsid") String clsid);
}
