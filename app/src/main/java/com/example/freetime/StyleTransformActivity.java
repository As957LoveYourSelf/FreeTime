package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.StyleTransformPresenter;
import com.example.freetime.view.IStyleTransformView;

public class StyleTransformActivity extends BaseActivity<StyleTransformPresenter, IStyleTransformView> implements IStyleTransformView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_transform);
    }

    @Override
    protected StyleTransformPresenter createPresenter() {
        return new StyleTransformPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void transform(byte[] img) {

    }
}