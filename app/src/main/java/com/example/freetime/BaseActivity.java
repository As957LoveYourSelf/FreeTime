package com.example.freetime;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freetime.presenter.BasePresenter;
import com.example.freetime.utils.BitmapUtils;
import com.example.freetime.utils.GlideEngine;
import com.example.freetime.view.IBaseView;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public abstract class BaseActivity<P extends BasePresenter, V extends IBaseView> extends AppCompatActivity {

    protected P presenter;
    protected PopupWindow pop;
    protected boolean hasresult;
    protected Bitmap realimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        hasresult = false;
        //使用模板生产Presenter对象
        presenter = createPresenter();
        // 自动绑定
        presenter.attachView((V)this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 自动解绑
        presenter.detachView();
    }

    protected void popupWindow(int show_view_id){
        View bottomView = View.inflate(BaseActivity.this, R.layout.popue_window, null);
        Button mAlbum = bottomView.findViewById(R.id.btn_pop_album);
        Button mCamera = bottomView.findViewById(R.id.btn_pop_camera);
        Button mCancel = bottomView.findViewById(R.id.btn_pop_cancel);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_pop_album:
                        //相册
                        PictureSelector.create(BaseActivity.this)
                                .openGallery(SelectMimeType.ofImage())
                                .setImageEngine(GlideEngine.createGlideEngine())
                                .setMaxSelectNum(1)
                                .forResult(new OnResultCallbackListener<LocalMedia>() {
                                    @Override
                                    public void onResult(ArrayList<LocalMedia> result) {
                                        ImageView img = findViewById(show_view_id);
                                        String path = result.get(0).getRealPath();
                                        File file = new File(path);
                                        if (file.exists()){
                                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                                            realimg = bitmap;
                                            bitmap = BitmapUtils.scaleImage(bitmap, img.getWidth(), img.getHeight());
                                            img.setImageBitmap(bitmap);
                                            hasresult = true;
                                            Toast.makeText(BaseActivity.this, "已选择图片", Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(BaseActivity.this, "路径不存在", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancel() {
                                    }
                                });
                        break;
                    case R.id.btn_pop_camera:
                        //拍照
                        PictureSelector.create(BaseActivity.this)
                                .openCamera(SelectMimeType.ofImage())
                                .forResult(new OnResultCallbackListener<LocalMedia>() {
                                    @Override
                                    public void onResult(ArrayList<LocalMedia> result) {
                                        ImageView img = findViewById(show_view_id);
                                        String path = result.get(0).getPath();
                                        File file = new File(path);
                                        if (file.exists()){
                                            Bitmap bitmap = BitmapFactory.decodeFile(path);
                                            realimg = bitmap;
                                            bitmap = BitmapUtils.scaleImage(bitmap, img.getWidth(), img.getHeight());
                                            img.setImageBitmap(bitmap);
                                            hasresult = true;
                                            Toast.makeText(BaseActivity.this, "已选择图片", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    @Override
                                    public void onCancel() {

                                    }
                                });
                        break;
                    case R.id.btn_pop_cancel:
                        //取消
                        closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };
        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    private void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }
}