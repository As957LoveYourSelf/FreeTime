package com.example.freetime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.freetime.presenter.SuperResolvePresenter;
import com.example.freetime.view.ISuperResolveView;


public class SuperResolveActivity extends BaseActivity<SuperResolvePresenter, ISuperResolveView> implements ISuperResolveView {

    ImageView view;
    ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_resolve);
        view = findViewById(R.id.img_sup_show);
        btn = findViewById(R.id.sup_btn);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.img_sup_show);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasresult){
                    Toast.makeText(SuperResolveActivity.this, "超分辨运行中，请稍等...", Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                    presenter.fetch(bitmap);
                }
            }
        });
    }

    @Override
    protected SuperResolvePresenter createPresenter() {
        return new SuperResolvePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void superResolve(byte[] img) {
        if (img != null){
            view.setImageBitmap(BitmapFactory.decodeByteArray(img,0,img.length));
            Toast.makeText(this, "转化成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "空数据", Toast.LENGTH_SHORT).show();
        }
    }

}