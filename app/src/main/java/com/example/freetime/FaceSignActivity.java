package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.ncnn.SCRFDNcnn;
import com.example.freetime.presenter.FaceSignPresenter;
import com.example.freetime.view.IFaceSignView;

import java.util.Map;

public class FaceSignActivity extends BaseActivity<FaceSignPresenter, IFaceSignView> implements SurfaceHolder.Callback, IFaceSignView {

    public static final int REQUEST_CAMERA = 100;
    private SCRFDNcnn scrfdNcnn = new SCRFDNcnn();
    private SurfaceView surfaceView;
    Thread thread;
    ImageView avatar;
    TextView unameTV;
    TextView idTV;
    Button btn;
    private int facing = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_sign);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        String classname = getIntent().getStringExtra("classname");
        DetectThread detectThread = new DetectThread(classname);
        initView();
        thread = new Thread(detectThread);
        thread.start();
    }

     class DetectThread implements Runnable{
        private String classname;
        public DetectThread(String classname){
            this.classname = classname;
        }

        @Override
        public void run() {
            while (true){
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
                this.detect();
            }
        }
        private void detect(){
            String saveState = scrfdNcnn.getSaveState();
            try {
//                System.out.println(saveState);
                if (saveState.equals("success")){
                    presenter.fetch(this.classname);
                    Thread.sleep(5000);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



    @Override
    protected FaceSignPresenter createPresenter() {
        return new FaceSignPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void signFace(Map<String, Object> response) {
        System.out.println(response);
        if (response != null){
            System.out.println("set info");
            String uname = (String) response.get("name");
            String id = (String) response.get("uno");
            showSignMessage(uname, id);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
        scrfdNcnn.openCamera(facing);
        if (thread.isInterrupted()){
            thread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        scrfdNcnn.closeCamera();
        thread.interrupt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

    private void showSignMessage(String uname, String id){
        unameTV.setText("姓名: "+uname);
        idTV.setText("学号: "+id);
    }

    private void initView(){
        surfaceView = findViewById(R.id.sign_face_view);
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);
        surfaceView.getHolder().addCallback(this);
        avatar = findViewById(R.id.avatar_sign_face);
        unameTV = findViewById(R.id.uname_sign);
        idTV = findViewById(R.id.id_sign);
        btn = findViewById(R.id.change_sign_camara);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_facing = 1 - facing;
                scrfdNcnn.closeCamera();
                scrfdNcnn.openCamera(new_facing);
                facing = new_facing;
            }
        });
        scrfdNcnn.loadModel(getAssets(), true);
    }
}