package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IChangePasswordModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.UserManageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePasswordModel implements IChangePasswordModel {

    private Map<String, String> postData;

    @Override
    public void setNewPassword(String psd, String uid) {
        this.postData = new HashMap<>();
        this.postData.put("uid", uid);
        this.postData.put("newPsd", psd);
    }

    @Override
    public void changePsd(OnLoaderListener onLoaderListener) throws InterruptedException {
        if (this.postData != null){
            UserManageService userManageService = RetrofitClient.getInstance().getService(UserManageService.class);
            userManageService
                    .changePsd(postData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                        @Override
                        public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                            if (Objects.equals(mapResponseBean.getMessage(), "success")){
                                onLoaderListener.onMapComplete(mapResponseBean.getData());
                            }
                        }
                    });
        }
    }

}
