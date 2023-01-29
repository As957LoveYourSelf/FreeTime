package com.example.freetime.presenter;
import android.widget.ImageButton;



public class MainPagePresenter extends BasePresenter {

    /**
     * 执行业务逻辑
     * 1 点击 Login按钮时，按钮图标变化，并切换至登录页面
     * 2 点击 Register按钮时，按钮图标变化，并切换至注册页面
     */

    // Login 按钮的变化以及登录页面的跳转
    public void mainPageBtnChange(ImageButton loginImageButton, int rid){
        loginImageButton.setBackgroundResource(rid);
    };
}
