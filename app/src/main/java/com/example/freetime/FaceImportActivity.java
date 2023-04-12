package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.freetime.ncnn.SCRFDNcnn;
import com.example.freetime.presenter.FaceImportPresenter;
import com.example.freetime.view.IFaceImportView;

public class FaceImportActivity extends BaseActivity<FaceImportPresenter, IFaceImportView> implements SurfaceHolder.Callback,IFaceImportView {

    public static final int REQUEST_CAMERA = 100;
    private SCRFDNcnn scrfdNcnn = new SCRFDNcnn();
    private SurfaceView surfaceView;
    Button confirm;
    Button change;
    private int facing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_import);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        surfaceView = findViewById(R.id.face_import_surface);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);
        surfaceView.getHolder().addCallback(this);

        change = findViewById(R.id.face_import_changCamara);
        confirm = findViewById(R.id.face_import_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 将截取的图像上传至数据库
                scrfdNcnn.cropFace();
                if (scrfdNcnn.getSaveState().equals("none")){
                    Toast.makeText(FaceImportActivity.this, "未检测到人脸", Toast.LENGTH_SHORT).show();
                }
                if (scrfdNcnn.getSaveState().equals("more")){
                    Toast.makeText(FaceImportActivity.this, "请确保只有您单独录入", Toast.LENGTH_SHORT).show();
                }
                if (scrfdNcnn.getSaveState().equals("success")){
                    presenter.fetch();
                    Toast.makeText(FaceImportActivity.this, "图像截取成功", Toast.LENGTH_SHORT).show();
                }
                if (scrfdNcnn.getSaveState() == null){
                    Toast.makeText(FaceImportActivity.this, "未知错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_facing = 1 - facing;
                scrfdNcnn.closeCamera();
                scrfdNcnn.openCamera(new_facing);
                facing = new_facing;
            }
        });
        scrfdNcnn.loadModel(getAssets(), false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
        scrfdNcnn.openCamera(facing);
    }

    @Override
    protected void onPause() {
        super.onPause();
        scrfdNcnn.closeCamera();

    }

    @Override
    protected FaceImportPresenter createPresenter() {
        return new FaceImportPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void importFace(String response) {
        if (response != null){
            if (response.equals("success")){
                Toast.makeText(this, "图像上传成功", Toast.LENGTH_SHORT).show();
            }else if (response.equals("fail")){
                Toast.makeText(this, "超过上传次数，请联系管理员进行重置", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        scrfdNcnn.setOutputWindow(holder.getSurface());
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}