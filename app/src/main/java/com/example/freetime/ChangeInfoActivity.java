package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.ChangeInfoPresenter;
import com.example.freetime.view.IChangeInfoView;

import java.util.Map;

public class ChangeInfoActivity extends BaseActivity<ChangeInfoPresenter, IChangeInfoView> implements IChangeInfoView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
    }

    @Override
    protected ChangeInfoPresenter createPresenter() {
        return new ChangeInfoPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void changeInfo(Map<String, Object> info) {

    }
}