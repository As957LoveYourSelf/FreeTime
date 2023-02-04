package com.example.freetime.presenter;

import com.example.freetime.model.interfaces.ILoginModel;
import com.example.freetime.model.LoginModel;
import com.example.freetime.utils.Md5Util;
import com.example.freetime.view.ILoginPageView;

import java.util.Map;

public class LoginPagePresenter extends BasePresenter<ILoginPageView>{
    /**
     * 1. 提交 UI 获得的用户信息，根据后台验证的登录状态返回登录结果
     */

    ILoginModel LoginModel = new LoginModel();

    public void fetch(String uname, String psd) throws Exception {
        if (mView.get() != null && LoginModel != null){
            LoginModel.setUname(uname);
            LoginModel.setPassword(Md5Util.encodeByMd5(psd));
            this.LoginModel.goLogin(new ILoginModel.OnLoaderListener() {
                @Override
                public void onComplete(Map<String, Object> info) {
                    // 通过该接口拿到数据传给Activity,在Activity里面实现对获取后台数据之后的处理
                    mView.get().goLogin(info);
                }
                @Override
                public void onErrMsg() {
                    // 显示错误信息

                }
            });
        }
    }
}
