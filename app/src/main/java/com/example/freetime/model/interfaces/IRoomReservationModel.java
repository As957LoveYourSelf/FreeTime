package com.example.freetime.model.interfaces;

public interface IRoomReservationModel extends IBaseModel {
    void reserve(OnLoaderListener onLoaderListener) throws InterruptedException;
    void dereserve(OnLoaderListener onLoaderListener) throws InterruptedException;
    void getRooms(OnLoaderListener onLoaderListener) throws InterruptedException;
}
