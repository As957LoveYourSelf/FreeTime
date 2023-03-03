package com.example.freetime.presenter;

import android.graphics.Bitmap;

import com.example.freetime.model.SuperResolveModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.ISuperResolveModel;
import com.example.freetime.view.ISuperResolveView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SuperResolvePresenter extends BasePresenter<ISuperResolveView>{

    ISuperResolveModel superResolveModel;
    public void fetch(Bitmap bitmap) {
        try {
            superResolveModel = new SuperResolveModel(bitmap);
            if (mView.get() != null && superResolveModel != null){
                superResolveModel.enhance(new IBaseModel.OnLoaderListener() {
                    @Override
                    public void onMapComplete(Map<String, Object> map) {

                    }

                    @Override
                    public void onListComplete(List<Object> list) {

                    }

                    @Override
                    public void onObjectComplete(Object obj) {
                        mView.get().superResolve((byte[]) obj);
                    }

                    @Override
                    public void onErrMsg() {

                    }
                });
            }
        }catch (IOException e){

        }
    }
}
