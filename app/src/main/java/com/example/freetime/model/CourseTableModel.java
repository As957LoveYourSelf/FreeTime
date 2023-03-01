package com.example.freetime.model;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.model.interfaces.ICourseTableModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.CourseTableService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.functions.Consumer;

public class CourseTableModel implements ICourseTableModel {

    private String uid;
    private String utype;

    @Override
    public void setUid(String uid, String utype) {
        this.uid = uid;
        this.utype = utype;
    }

    @Override
    public void getTable(OnLoaderListener onLoaderListener) {
        onLoaderListener.onMapComplete(getTable());
    }

    public Map<String, Object> getTable(){
        Map<String, Object> response = new HashMap<>();
        if (uid != null){
            CourseTableService courseTableService = RetrofitClient.getInstance().getService(CourseTableService.class);
            if (Objects.equals(this.utype, "teacher")){
                courseTableService.getTableByTno(uid).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
                    @Override
                    public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                        if (Objects.equals(mapBaseBean.getMessage(), "success")){
                            response.put("table", mapBaseBean.getData());
                        }
                    }
                });
            }

            if (Objects.equals(this.utype, "student")){
                courseTableService.getTableBySno(uid).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
                    @Override
                    public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                        if (Objects.equals(mapBaseBean.getMessage(), "success")){
                            response.put("table", mapBaseBean.getData());
                        }
                    }
                });
            }

        }
        return response;
    }
}
