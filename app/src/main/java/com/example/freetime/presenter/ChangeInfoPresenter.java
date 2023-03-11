package com.example.freetime.presenter;

import com.example.freetime.beans.User;
import com.example.freetime.model.ChangeInfoModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IChangeInfoModel;
import com.example.freetime.view.IChangeInfoView;

import java.util.List;
import java.util.Map;

public class ChangeInfoPresenter extends BasePresenter<IChangeInfoView>{

    public void fetch(String uid, Map<String, Object> info){
        IChangeInfoModel iChangeInfoModel = new ChangeInfoModel(uid, info);
        if (mView.get() != null){
            iChangeInfoModel.changeInfo(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {
                    mView.get().changeInfo(map);
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
