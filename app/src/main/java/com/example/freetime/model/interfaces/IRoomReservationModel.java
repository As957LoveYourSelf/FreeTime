package com.example.freetime.model.interfaces;

public interface IRoomReservationModel extends IBaseModel {
    void setUid(String uid, String clsid);
    void setParams(String buildingName, Integer isOrder, Integer floor);
    void reserve(OnLoaderListener onLoaderListener);
    void dereserve(OnLoaderListener onLoaderListener);
    void getRooms(OnLoaderListener onLoaderListener);
}
