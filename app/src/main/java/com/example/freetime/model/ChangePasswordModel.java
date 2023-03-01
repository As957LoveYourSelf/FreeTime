package com.example.freetime.model;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.model.interfaces.IChangePasswordModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.UserManageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.functions.Consumer;

public class ChangePasswordModel implements IChangePasswordModel {

    private Map<String, String> postData;

    @Override
    public void setNewPassword(String psd, String uid) {
        this.postData = new HashMap<>();
        this.postData.put("uid", uid);
        this.postData.put("newPsd", psd);
    }

    @Override
    public void changePsd(OnLoaderListener onLoaderListener) {
        onLoaderListener.onMapComplete(change());
    }

    private Map<String, Object> change(){
        Map<String, Object> response = new HashMap<>();
        if (this.postData != null){
            UserManageService userManageService = RetrofitClient.getInstance().getService(UserManageService.class);
            userManageService.changePsd(postData).subscribe(mapBaseBean -> {
                if (Objects.equals(mapBaseBean.getMessage(), "success")){
                    Map<String, Object> data = mapBaseBean.getData();
                    response.put("status", data.get("status"));
                }
            });
        }
        return response;
    }
}
