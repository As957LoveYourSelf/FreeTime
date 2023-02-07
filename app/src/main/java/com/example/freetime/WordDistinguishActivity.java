package com.example.freetime;

import android.os.Bundle;

import com.example.freetime.presenter.WordDistinguishPresenter;
import com.example.freetime.view.IWordDistinguishView;

public class WordDistinguishActivity extends BaseActivity<WordDistinguishPresenter, IWordDistinguishView> implements IWordDistinguishView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_distinguish);
    }

    @Override
    protected WordDistinguishPresenter createPresenter() {
        return null;
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}