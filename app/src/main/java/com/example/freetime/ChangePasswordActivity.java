package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.ChangePasswordPresenter;
import com.example.freetime.view.IChangePasswordView;

import java.util.Map;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter, IChangePasswordView> implements IChangePasswordView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
    }

    @Override
    protected ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void changePsd(Map<String, Object> info) {

    }
}