package com.example.freetime.model.interfaces;

import java.util.Map;

public interface IUserManageModel {
    interface OnLoaderListener{
        void onComplete(Map<String, Object> map);
        void onErrMsg();
    }
}
