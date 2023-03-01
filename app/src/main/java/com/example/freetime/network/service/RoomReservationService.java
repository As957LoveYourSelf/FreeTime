package com.example.freetime.network.service;

import com.example.freetime.beans.BaseBean;

import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RoomReservationService {
    @GET("classroomManage/getClassroomInfo")
    Flowable<BaseBean<List<Object>>> getRooms(String buildingName, Integer isOrder, Integer floor);

    @POST("classroomManage/orderClassroom")
    Flowable<BaseBean<String>> reserve(String uid, String clsid);

    @POST("classroomManage/deorderClassroom")
    Flowable<BaseBean<String>> dereserve(String uid, String clsid);
}
