package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.SuperResolvePresenter;
import com.example.freetime.view.ISuperResolveView;

public class SuperResolveActivity extends BaseActivity<SuperResolvePresenter, ISuperResolveView> implements ISuperResolveView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_resolve);

    }

    @Override
    protected SuperResolvePresenter createPresenter() {
        return new SuperResolvePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void superResolve(byte[] img) {

    }
}