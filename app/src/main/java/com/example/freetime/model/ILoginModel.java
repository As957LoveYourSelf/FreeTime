package com.example.freetime.model;

import com.example.freetime.entity.User;

/**
 * 提供登录请求接口
 * */
public interface ILoginModel extends IBaseModel{
    void goLogin(OnLoaderListener onLoaderListener);
    interface OnLoaderListener{
        void onComplete(User user);
        void onErrMsg();
    }
}
