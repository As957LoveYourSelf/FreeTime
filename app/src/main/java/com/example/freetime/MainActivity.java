package com.example.freetime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.freetime.presenter.MainPagePresenter;
import com.example.freetime.view.IMainPageView;

public class MainActivity extends BaseActivity<MainPagePresenter, IMainPageView> implements IMainPageView{

    private ImageButton m_l_iBtn;
    private boolean changeStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_FreeTime);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeStatus = false;
        m_l_iBtn = findViewById(R.id.main_login_btn_before);
        MainPagePresenter mainPagePresenter = createPresenter();
        this.m_l_iBtn.setOnClickListener(v -> {
            if (changeStatus){
                mainPagePresenter.mainPageBtnChange(m_l_iBtn, R.drawable.main_login_btn);
            }else {
                mainPagePresenter.mainPageBtnChange(m_l_iBtn, R.drawable.login_btn_after_en);
                changeStatus = true;
            }
            startLoginPage();
        });
    }

    @Override
    protected MainPagePresenter createPresenter() {
        return new MainPagePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        m_l_iBtn.setBackgroundResource(R.drawable.main_login_btn);
        changeStatus = false;
    }

    @Override
    public void startLoginPage() {
        startActivity(new Intent(this, LoginActivity.class));
    }
}