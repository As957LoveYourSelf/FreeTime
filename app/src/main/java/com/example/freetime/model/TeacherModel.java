package com.example.freetime.model;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.model.interfaces.ITeacherModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.TeacherService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;

public class TeacherModel extends UserManageModel implements ITeacherModel {

    private String tno;

    @Override
    public void setTno(String tno) {
        this.tno = tno;
    }

    @Override
    public void getTeacherInfo(OnLoaderListener onLoaderListener) {
        onLoaderListener.onMapComplete(getInfo());
    }

    private Map<String, Object> getInfo(){
        Map<String, Object> response = new HashMap<>();
        if (tno != null){
            TeacherService service = RetrofitClient.getInstance().getService(TeacherService.class);
            service.getInfo(tno).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
                @Override
                public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                    response.put("info", mapBaseBean.getData());
                }
            });
        }
        return response;
    }
}
