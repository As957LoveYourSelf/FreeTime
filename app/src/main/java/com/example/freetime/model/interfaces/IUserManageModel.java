package com.example.freetime.model.interfaces;

import java.util.Map;

public interface IUserManageModel {
    void changeInfo(OnLoaderListener onloaderListener);
    void changePassword(OnLoaderListener onLoaderListener);
    interface OnLoaderListener{
        void onComplete(Map<String, Object> map);
        void onErrMsg();
    }
}
