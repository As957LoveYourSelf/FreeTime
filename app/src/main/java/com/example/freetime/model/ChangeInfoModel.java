package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.beans.User;
import com.example.freetime.model.interfaces.IChangeInfoModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.UserManageService;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ChangeInfoModel implements IChangeInfoModel {

    private Map<String, Object> postData;
    String uid;

    public ChangeInfoModel(String uid, Map<String, Object> info){
        this.uid = uid;
        this.postData = info;
    }

    @Override
    public void changeInfo(OnLoaderListener onLoaderListener) {
        if (this.postData != null){
            UserManageService service = RetrofitClient.getInstance().getService(UserManageService.class);
            service.changeInfo(this.uid, this.postData)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                        @Override
                        public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                            if (Objects.equals(mapResponseBean.getMessage(), "success")) {
                                onLoaderListener.onMapComplete(mapResponseBean.getData());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }
}
