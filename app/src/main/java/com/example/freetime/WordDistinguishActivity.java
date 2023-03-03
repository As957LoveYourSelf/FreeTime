package com.example.freetime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.freetime.presenter.WordDistinguishPresenter;
import com.example.freetime.view.IWordDistinguishView;

import java.util.List;

public class WordDistinguishActivity extends BaseActivity<WordDistinguishPresenter, IWordDistinguishView> implements IWordDistinguishView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_distinguish);
        ImageView view = findViewById(R.id.word_dis_show);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.word_dis_show);
            }
        });
    }

    @Override
    protected WordDistinguishPresenter createPresenter() {
        return new WordDistinguishPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void distinguish(List<Object> result) {

    }
}