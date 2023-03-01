package com.example.freetime.model;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.beans.User;
import com.example.freetime.model.interfaces.IChangeInfoModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.UserManageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.functions.Consumer;

public class ChangeInfoModel implements IChangeInfoModel {

    private Map<String, Object> postData;

    public ChangeInfoModel(){

    }

    @Override
    public void setNewInfo(User newInfo) {
        this.postData = new HashMap<>();
        this.postData.put("newInfo", newInfo);
    }

    @Override
    public void changeInfo(OnLoaderListener onLoaderListener) {
        onLoaderListener.onMapComplete(change());
    }

    private Map<String, Object> change(){
        Map<String, Object> response = new HashMap<>();
        if (this.postData != null){
            UserManageService service = RetrofitClient.getInstance().getService(UserManageService.class);
            service.changeInfo(this.postData).subscribe(mapBaseBean -> {
                if (Objects.equals(mapBaseBean.getMessage(), "success")){
                    Map<String, Object> data = mapBaseBean.getData();
                    response.put("newInfo", data.get("newInfo"));
                }
            });
        }
        return response;
    }
}
