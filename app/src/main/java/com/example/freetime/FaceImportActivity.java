package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.FaceImportPresenter;
import com.example.freetime.view.IFaceImportView;

public class FaceImportActivity extends BaseActivity<FaceImportPresenter, IFaceImportView> implements IFaceImportView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_import);

    }

    @Override
    protected FaceImportPresenter createPresenter() {
        return new FaceImportPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void importFace() {

    }
}