package com.example.freetime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.freetime.presenter.ColorizationPresenter;
import com.example.freetime.view.IWordDistinguishView;

import java.util.List;

public class ColorizationActivity extends BaseActivity<ColorizationPresenter, IWordDistinguishView> implements IWordDistinguishView {


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
    protected ColorizationPresenter createPresenter() {
        return new ColorizationPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {
        if (msg != null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void distinguish(List<Object> result) {

    }
}