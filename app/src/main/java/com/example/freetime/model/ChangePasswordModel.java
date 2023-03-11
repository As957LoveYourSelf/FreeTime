package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IChangePasswordModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.UserManageService;
import com.example.freetime.utils.Md5Util;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangePasswordModel implements IChangePasswordModel {

    private Map<String, String> postData;

    @Override
    public void setNewPassword(String psd, String uid) throws Exception {
        this.postData = new HashMap<>();
        this.postData.put("uid", uid);
        this.postData.put("newPsd", Md5Util.encodeByMd5(psd));
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
                    },new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }

}
