package com.example.freetime.model;

import android.annotation.SuppressLint;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IRoomReservationModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.RoomReservationService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RoomReservationModel implements IRoomReservationModel {

    private String uid;
    private String clsid;
    private String buildingName;
    private Integer isOrder;
    private Integer floor;

    public RoomReservationModel(){

    }
    public RoomReservationModel(String bname){
        this.buildingName = bname;
    }

    public RoomReservationModel(String uid, String clsid){
        this.uid = uid;
        this.clsid = clsid;
    }

    public RoomReservationModel(String buildingName, Integer isOrder, Integer floor){
        this.buildingName = buildingName;
        this.isOrder = isOrder;
        this.floor = floor;
    }

    @SuppressLint("CheckResult")
    @Override
    public void reserve(OnLoaderListener onLoaderListener) {
        if (this.clsid != null && this.uid != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.reserve(uid, clsid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<String>>() {
                        @Override
                        public void accept(ResponseBean<String> responseBean) throws Throwable {
                            onLoaderListener.onObjectComplete(responseBean.getData());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void dereserve(OnLoaderListener onLoaderListener) {
        if (this.clsid != null && this.uid != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.dereserve(uid, clsid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<String>>() {
                        @Override
                        public void accept(ResponseBean<String> stringResponseBean) throws Throwable {
                            onLoaderListener.onObjectComplete(stringResponseBean.getData());
                        }
                    },new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }

    @Override
    public void getRooms(OnLoaderListener onLoaderListener) {
        if (buildingName != null && isOrder != null && floor != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.getRooms(buildingName, isOrder, floor)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<List<Object>>>() {
                        @Override
                        public void accept(ResponseBean<List<Object>> listResponseBean) throws Throwable {
                            onLoaderListener.onListComplete(listResponseBean.getData());
                        }
                    },new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }

    @Override
    public void getBuildingSelector(OnLoaderListener onLoaderListener) {
        RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
        service.getBuildingSelector().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<List<Object>>>() {
                    @Override
                    public void accept(ResponseBean<List<Object>> listResponseBean) throws Throwable {
                        onLoaderListener.onListComplete(listResponseBean.getData());
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        onLoaderListener.onErrMsg("网络请求错误");
                    }
                });
    }

    @Override
    public void getBuildingFloor(OnLoaderListener onLoaderListener) {
        RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
        service.getBuildingFloor(buildingName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<List<Object>>>() {
                    @Override
                    public void accept(ResponseBean<List<Object>> listResponseBean) throws Throwable {
                        onLoaderListener.onListComplete(listResponseBean.getData());
                    }
                },new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        onLoaderListener.onErrMsg("网络请求错误");
                    }
                });

    }
}
