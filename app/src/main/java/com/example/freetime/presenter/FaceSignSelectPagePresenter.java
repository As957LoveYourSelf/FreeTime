package com.example.freetime.presenter;

import com.example.freetime.model.FaceSignSelectPageModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IFaceSignSelectPageModel;
import com.example.freetime.view.IFaceSignSelectPageView;

import java.util.List;
import java.util.Map;

public class FaceSignSelectPagePresenter extends BasePresenter<IFaceSignSelectPageView>{
    IFaceSignSelectPageModel faceSignSelectPageModel = new FaceSignSelectPageModel();

    public void fetch(){
        if (faceSignSelectPageModel != null && mView.get() != null){
            faceSignSelectPageModel.getClassSelector(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {
                    mView.get().getSignClasses(map);
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
