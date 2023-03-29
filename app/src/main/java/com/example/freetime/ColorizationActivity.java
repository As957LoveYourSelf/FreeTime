package com.example.freetime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.freetime.presenter.ColorizationPresenter;
import com.example.freetime.utils.BitmapUtils;
import com.example.freetime.view.IColorizationView;

public class ColorizationActivity extends BaseActivity<ColorizationPresenter, IColorizationView> implements IColorizationView {

    ImageButton btn;
    ImageView view;
    Button save_btn;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorization);
        view = findViewById(R.id.colorization_show);
        btn = findViewById(R.id.colorization_btn);
        save_btn = findViewById(R.id.colorization_img_save);
        save_btn.setVisibility(View.GONE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.colorization_show);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasresult){
                    Toast.makeText(ColorizationActivity.this, "图片上色运行中，请稍等...", Toast.LENGTH_SHORT).show();
                    presenter.fetch(realimg);
                }
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
    public void colorization(byte[] img) {
        if (img != null){
            Bitmap bitmap = BitmapFactory.decodeByteArray(img,0,img.length);
            Bitmap finalBitmap = bitmap;
            save_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String result = BitmapUtils.saveBitmapToLocal(finalBitmap, "SuperResolve");
                    if (result != null){
                        Toast.makeText(ColorizationActivity.this, "图片已保存至"+result, Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ColorizationActivity.this, "图片保存失败", Toast.LENGTH_SHORT).show();
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
}