package com.example.freetime.view;

import com.example.freetime.model.interfaces.IBaseModel;

import java.util.List;
import java.util.Map;

public interface IRoomReservationView extends IBaseView{
    void reserve(String status);
    void dereserve(String status);
    void getRooms(List<Object> rooms);
}
