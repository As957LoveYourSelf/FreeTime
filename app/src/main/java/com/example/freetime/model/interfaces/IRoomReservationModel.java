package com.example.freetime.model.interfaces;

public interface IRoomReservationModel extends IBaseModel {
    void reserve(OnLoaderListener onLoaderListener);
    void dereserve(OnLoaderListener onLoaderListener);
    void getRooms(OnLoaderListener onLoaderListener);
    void getBuildingSelector(OnLoaderListener onLoaderListener);
    void getBuildingFloor(OnLoaderListener onLoaderListener);
}
