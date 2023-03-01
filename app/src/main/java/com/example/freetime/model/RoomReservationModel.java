package com.example.freetime.model;

import com.example.freetime.model.interfaces.IRoomReservationModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.RoomReservationService;

import java.util.ArrayList;
import java.util.List;


public class RoomReservationModel implements IRoomReservationModel {

    private String uid;
    private String clsid;
    private String buildingName;
    private Integer isOrder;
    private Integer floor;

    @Override
    public void setUid(String uid, String clsid) {
        this.uid = uid;
        this.clsid = clsid;
    }

    @Override
    public void setParams(String buildingName, Integer isOrder, Integer floor) {
        this.buildingName = buildingName;
        this.isOrder = isOrder;
        this.floor = floor;
    }

    @Override
    public void reserve(OnLoaderListener onLoaderListener) {
        onLoaderListener.onObjectComplete(reserve());
    }

    @Override
    public void dereserve(OnLoaderListener onLoaderListener) {
        onLoaderListener.onObjectComplete(dereserve());
    }

    @Override
    public void getRooms(OnLoaderListener onLoaderListener) {
        onLoaderListener.onListComplete(getRooms());
    }

    private String reserve(){
        final String[] response = new String[1];
        if (this.clsid != null && this.uid != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.reserve(uid, clsid).subscribe(stringBaseBean -> response[0] = stringBaseBean.getData());
        }
        return response[0];
    }

    private String dereserve(){
        final String[] response = new String[1];
        if (this.clsid != null && this.uid != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.dereserve(uid, clsid).subscribe(stringBaseBean -> response[0] = stringBaseBean.getData());
        }
        return response[0];
    }

    private List<Object> getRooms(){
        final List<Object>[] response = new List[]{new ArrayList<>()};
        if (buildingName != null && isOrder != null && floor != null){
            RoomReservationService service = RetrofitClient.getInstance().getService(RoomReservationService.class);
            service.getRooms(buildingName, isOrder, floor).subscribe(mapBaseBean -> response[0] = mapBaseBean.getData());
        }
        return response[0];
    }
}
