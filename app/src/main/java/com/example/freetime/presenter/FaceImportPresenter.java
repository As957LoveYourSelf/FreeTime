package com.example.freetime.presenter;

import com.example.freetime.model.FaceImportModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IFaceImportModel;
import com.example.freetime.view.IFaceImportView;

import java.util.List;
import java.util.Map;

public class FaceImportPresenter extends BasePresenter<IFaceImportView>{

    IFaceImportModel faceImportModel = new FaceImportModel();

    public void fetch(){
        if (faceImportModel != null && mView.get() != null){
            faceImportModel.importFace(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {
                    String response = (String) obj;
                    mView.get().importFace(response);
                }

                @Override
                public void onErrMsg(String msg) {
                    mView.get().showErrorMessage(msg);
                }
            });
        }
    }
}
