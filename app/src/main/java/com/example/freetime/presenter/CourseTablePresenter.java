package com.example.freetime.presenter;

import com.example.freetime.model.CourseTableModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.ICourseTableModel;
import com.example.freetime.view.ICourseTableView;

import java.util.List;
import java.util.Map;

public class CourseTablePresenter extends BasePresenter<ICourseTableView>{
    ICourseTableModel courseTableModel = new CourseTableModel();

    public void fetch(String uid, String utype){
        if (courseTableModel != null && mView.get() != null){
            courseTableModel.setUid(uid, utype);
            courseTableModel.getTable(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> info) {
                    mView.get().getTable(info);
                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {

                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }
}
