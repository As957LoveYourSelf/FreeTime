package com.example.freetime.view;

import java.util.List;

public interface IRoomReservationView extends IBaseView{
    void reserve(String status);
    void dereserve(String status);
    void getRooms(List<Object> rooms);
    void getBuildingSelector(List<Object> builds);
    void getBuildingFloorSelector(List<Object> floors);
}
