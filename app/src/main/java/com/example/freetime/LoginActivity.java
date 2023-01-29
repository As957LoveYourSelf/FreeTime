package com.example.freetime;

import android.os.Bundle;

import com.example.freetime.presenter.LoginPagePresenter;
import com.example.freetime.view.ILoginPageView;

public class LoginActivity extends BaseActivity<LoginPagePresenter, ILoginPageView> implements ILoginPageView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected LoginPagePresenter createPresenter() {
        return new LoginPagePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void goLogin() {

    }
}