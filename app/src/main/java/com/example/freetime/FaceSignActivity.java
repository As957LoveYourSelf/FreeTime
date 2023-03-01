package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.beans.User;
import com.example.freetime.presenter.FaceSignPresenter;
import com.example.freetime.view.IFaceSignView;

public class FaceSignActivity extends BaseActivity<FaceSignPresenter, IFaceSignView> implements IFaceSignView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_sign);
    }

    @Override
    protected FaceSignPresenter createPresenter() {
        return new FaceSignPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void signFace(User user) {

    }
}