package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.ITeacherModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.TeacherService;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class TeacherModel extends UserManageModel implements ITeacherModel {

    private String tno;

    public TeacherModel(String tno){
        this.tno = tno;
    }

    @Override
    public void getTeacherInfo(OnLoaderListener onLoaderListener) {
        Map<String, Object> response = new HashMap<>();
        if (tno != null){
            TeacherService service = RetrofitClient.getInstance().getService(TeacherService.class);
            service.getInfo(tno)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                @Override
                public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                    response.put("info", mapResponseBean.getData());
                    onLoaderListener.onMapComplete(response);
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
