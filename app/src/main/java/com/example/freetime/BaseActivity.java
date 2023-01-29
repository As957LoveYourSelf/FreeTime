package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.BasePresenter;
import com.example.freetime.view.IBaseView;

public abstract class BaseActivity<T extends BasePresenter, V extends IBaseView> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        //使用模板生产Presenter对象
        presenter = createPresenter();
        // 自动绑定
        presenter.attachView((V)this);
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 自动解绑
        presenter.detachView();
    }
}