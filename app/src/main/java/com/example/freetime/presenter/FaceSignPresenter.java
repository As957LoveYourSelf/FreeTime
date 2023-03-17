package com.example.freetime.presenter;

import com.example.freetime.model.FaceImportModel;
import com.example.freetime.model.FaceSignModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IFaceImportModel;
import com.example.freetime.model.interfaces.IFaceSignModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.FaceSignService;
import com.example.freetime.view.IFaceSignView;

import java.util.List;
import java.util.Map;

public class FaceSignPresenter extends BasePresenter<IFaceSignView>{


    public void fetch(String classname){
        IFaceSignModel faceSignModel = new FaceSignModel(classname);
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
}
