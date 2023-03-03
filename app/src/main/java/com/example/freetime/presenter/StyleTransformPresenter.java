package com.example.freetime.presenter;

import android.graphics.Bitmap;

import com.example.freetime.model.StyleTransformModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IStyleTransformModel;
import com.example.freetime.view.IStyleTransformView;

import java.util.List;
import java.util.Map;

public class StyleTransformPresenter extends BasePresenter<IStyleTransformView>{

    IStyleTransformModel styleTransformModel;

    public void defaultStyleTransform(Bitmap bitmap, Integer type){
        styleTransformModel = new StyleTransformModel(bitmap, type);
        if (mView.get() != null && styleTransformModel != null){
            styleTransformModel.defaultStyleTransform(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {
                    mView.get().defaultStyleTransform((byte[]) obj);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }

    }

    public void anyStyleTransform(Bitmap context, Bitmap style){
        styleTransformModel = new StyleTransformModel(context, style);
        if (mView.get() != null && styleTransformModel != null){
            styleTransformModel.anyStyleTransform(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {

                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {
                    mView.get().anyStyleTransform((byte[]) obj);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }

    }

}
