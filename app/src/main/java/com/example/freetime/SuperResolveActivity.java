package com.example.freetime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.freetime.presenter.SuperResolvePresenter;
import com.example.freetime.utils.BitmapUtils;
import com.example.freetime.view.ISuperResolveView;


public class SuperResolveActivity extends BaseActivity<SuperResolvePresenter, ISuperResolveView> implements ISuperResolveView {

    ImageView view;
    ImageButton btn;
    Button save_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_resolve);
        initView();
    }

    @Override
    protected SuperResolvePresenter createPresenter() {
        return new SuperResolvePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {
        if (msg != null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void superResolve(byte[] img) {
        if (img != null){
            hasresult = false;
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            Bitmap finalBitmap = bitmap;
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String result = BitmapUtils.saveBitmapToLocal(finalBitmap, "SuperResolve");
                    if (result != null){
                        Toast.makeText(SuperResolveActivity.this, "图片已保存至"+result, Toast.LENGTH_SHORT).show();
                        save_btn.setVisibility(View.GONE);
                    }else {
                        Toast.makeText(SuperResolveActivity.this, "图片保存失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            bitmap = BitmapUtils.scaleImage(bitmap,view.getWidth(),view.getHeight());
            view.setImageBitmap(bitmap);
            save_btn.setVisibility(View.VISIBLE);
            Toast.makeText(this, "转化成功！", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "空数据", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView(){
        view = findViewById(R.id.img_sup_show);
        btn = findViewById(R.id.sup_btn);
        save_btn = findViewById(R.id.sup_img_save);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.img_sup_show,save_btn);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasresult){
                    Toast.makeText(SuperResolveActivity.this, "超分辨运行中，请稍等...", Toast.LENGTH_SHORT).show();
                    presenter.fetch(realimg);
                }else {
                    Toast.makeText(SuperResolveActivity.this, "请选择一张照片", Toast.LENGTH_SHORT).show();
                }
            }
        });
        save_btn.setVisibility(View.GONE);
    }

}