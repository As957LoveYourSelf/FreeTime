package com.example.freetime.presenter;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.model.ColorizationModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.view.IColorizationView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ColorizationPresenter extends BasePresenter<IColorizationView>{
    ColorizationModel colorizationModel;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void fetch(Bitmap bitmap) {
        try {
            colorizationModel = new ColorizationModel(bitmap);
            if (mView.get() != null && colorizationModel != null){
                colorizationModel.colorization(new IBaseModel.OnLoaderListener() {
                    @Override
                    public void onMapComplete(Map<String, Object> map) {

                    }

                    @Override
                    public void onListComplete(List<Object> list) {

                    }

                    @Override
                    public void onObjectComplete(Object obj) {
                        mView.get().colorization((byte[]) obj);
                    }

                    @Override
                    public void onErrMsg(String msg) {
                        mView.get().showErrorMessage(msg);
                    }
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
