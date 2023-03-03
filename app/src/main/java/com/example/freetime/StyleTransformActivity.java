package com.example.freetime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.freetime.presenter.StyleTransformPresenter;
import com.example.freetime.view.IStyleTransformView;

public class StyleTransformActivity extends BaseActivity<StyleTransformPresenter, IStyleTransformView> implements IStyleTransformView {

    ImageView view;
    ImageButton trans_btn;
    ImageButton choose_style_btn;
    int[] type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_transform);
        view = findViewById(R.id.style_tran_img_show);
        trans_btn = findViewById(R.id.trans_btn);
        choose_style_btn = findViewById(R.id.choose_style_btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.style_tran_img_show);
            }
        });
        trans_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasresult){

                }
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
    public void defaultStyleTransform(byte[] result) {
        if (result != null){

        }
    }

    @Override
    public void anyStyleTransform(byte[] result) {
        if (result != null){

        }
    }
}