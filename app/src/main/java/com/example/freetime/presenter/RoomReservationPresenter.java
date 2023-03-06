package com.example.freetime.presenter;

import com.example.freetime.model.RoomReservationModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IRoomReservationModel;
import com.example.freetime.view.IRoomReservationView;

import java.util.List;
import java.util.Map;

public class RoomReservationPresenter extends BasePresenter<IRoomReservationView> {


    public void getRooms(String buildingName, Integer isOrder, Integer floor) throws InterruptedException {
        IRoomReservationModel roomReservationModel = new RoomReservationModel(buildingName, isOrder, floor);
        if (roomReservationModel != null && mView.get() != null){
            roomReservationModel.getRooms(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> rooms) {
                    mView.get().getRooms(rooms);
                }

                @Override
                public void onObjectComplete(Object obj) {

                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }

    public void reserve(String uid, String clsid) throws InterruptedException {
        IRoomReservationModel roomReservationModel = new RoomReservationModel(uid, clsid);
        if (mView.get() != null){
            roomReservationModel.reserve(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object status) {
                    mView.get().reserve((String)status);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }

    public void dereserve(String uid, String clsid) throws InterruptedException {
        IRoomReservationModel roomReservationModel = new RoomReservationModel(uid, clsid);
        if (mView.get() != null){
            roomReservationModel.dereserve(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object status) {
                    mView.get().reserve((String)status);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }
}
