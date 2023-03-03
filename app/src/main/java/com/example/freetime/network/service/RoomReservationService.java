package com.example.freetime.network.service;

import com.example.freetime.beans.ResponseBean;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RoomReservationService {
    @GET("classroomManage/getClassroomInfo")
    Flowable<ResponseBean<List<Object>>> getRooms(String buildingName, Integer isOrder, Integer floor);

    @POST("classroomManage/orderClassroom")
    Flowable<ResponseBean<String>> reserve(String uid, String clsid);

    @POST("classroomManage/deorderClassroom")
    Flowable<ResponseBean<String>> dereserve(String uid, String clsid);
}
