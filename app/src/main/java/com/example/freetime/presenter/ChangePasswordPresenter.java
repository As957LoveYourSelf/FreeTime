package com.example.freetime.presenter;

import com.example.freetime.model.ChangePasswordModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IChangePasswordModel;
import com.example.freetime.view.IChangePasswordView;

import java.util.List;
import java.util.Map;

public class ChangePasswordPresenter extends BasePresenter<IChangePasswordView>{
    IChangePasswordModel changePasswordModel = new ChangePasswordModel();

    public void fetch(String psd, String uid) throws InterruptedException {
        if (changePasswordModel != null && mView.get() != null){
            changePasswordModel.setNewPassword(psd, uid);
            changePasswordModel.changePsd(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> info) {
                    mView.get().changePsd(info);
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
