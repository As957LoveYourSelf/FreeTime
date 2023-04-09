package com.example.freetime.presenter;

import com.example.freetime.model.FaceSignModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IFaceSignModel;
import com.example.freetime.view.IFaceSignView;

import java.util.List;
import java.util.Map;

public class FaceSignPresenter extends BasePresenter<IFaceSignView>{


    public void fetch(String classname, String course){
        IFaceSignModel faceSignModel = new FaceSignModel(classname, course);
        if (mView.get() != null){
            faceSignModel.signFace(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {
                    mView.get().signFace(map);
                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {

                }

                @Override
                public void onErrMsg(String msg) {

                }
            });
        }
    }

    public void getDetail(String classname){
        IFaceSignModel faceSignModel = new FaceSignModel(classname);
        if (mView.get() != null){
            faceSignModel.getDetail(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {
                    mView.get().getDetail(map);
                }

                @Override
                public void onListComplete(List<Object> list) {
                }

                @Override
                public void onObjectComplete(Object obj) {

                }

                @Override
                public void onErrMsg(String msg) {
                    mView.get().showErrorMessage(msg);
                }
            });
        }
    }
}
