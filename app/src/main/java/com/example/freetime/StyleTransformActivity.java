package com.example.freetime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.freetime.presenter.StyleTransformPresenter;
import com.example.freetime.view.IStyleTransformView;

public class StyleTransformActivity extends BaseActivity<StyleTransformPresenter, IStyleTransformView> implements IStyleTransformView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_transform);
        ImageView view = findViewById(R.id.style_tran_img_show);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.style_tran_img_show);
            }
        });
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