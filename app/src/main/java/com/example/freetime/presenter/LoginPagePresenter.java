package com.example.freetime.presenter;

import com.example.freetime.entity.User;
import com.example.freetime.model.ILoginModel;
import com.example.freetime.model.LoginModel;
import com.example.freetime.view.ILoginPageView;

public class LoginPagePresenter extends BasePresenter<ILoginPageView>{
    /**
     * 1. 提交 UI 获得的用户信息，根据后台验证的登录状态返回登录结果
     */

    ILoginModel iLoginModel = new LoginModel();


    public void fetch(){
        if (mView.get() != null && iLoginModel != null){
            this.iLoginModel.goLogin(new ILoginModel.OnLoaderListener() {
                @Override
                public void onComplete(User user) {
                    // 通过该接口拿到数据传给Activity

                }

                @Override
                public void onErrMsg() {
                    // 显示错误信息
                }
            });
        }
    }
}
