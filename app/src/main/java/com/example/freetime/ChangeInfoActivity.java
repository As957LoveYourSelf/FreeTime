package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.ChangeInfoPresenter;
import com.example.freetime.view.IChangeInfoView;

public class ChangeInfoActivity extends BaseActivity<ChangeInfoPresenter, IChangeInfoView> implements IChangeInfoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
    }

    @Override
    protected ChangeInfoPresenter createPresenter() {
        return null;
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}