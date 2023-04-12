package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.ncnn.SCRFDNcnn;
import com.example.freetime.presenter.FaceSignPresenter;
import com.example.freetime.utils.CustomRecycleViewAdapter;
import com.example.freetime.utils.ImageUtils;
import com.example.freetime.view.IFaceSignView;
import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.dialogs.BottomDialog;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopMenu;
import com.kongzue.dialogx.interfaces.OnBindView;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.style.IOSStyle;
import com.kongzue.dialogx.style.MaterialStyle;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FaceSignActivity extends BaseActivity<FaceSignPresenter, IFaceSignView> implements SurfaceHolder.Callback, IFaceSignView {

    public static final int REQUEST_CAMERA = 100;
    private SCRFDNcnn scrfdNcnn = new SCRFDNcnn();
    private SurfaceView surfaceView;
    Thread thread;
    ImageView avatar;
    TextView unameTV;
    TextView idTV;
    Button btn;
    Button btn2;
    Button btn3;
    String classname;
    String course;
    private int facing = 0;
    RecyclerView recyclerView1;
    RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_sign);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        classname = getIntent().getStringExtra("classname");
        course = getIntent().getStringExtra("course");
        DetectThread detectThread = new DetectThread(classname, course);
        initView();
        thread = new Thread(detectThread);
        thread.start();
    }

     class DetectThread implements Runnable{
        private final String classname;
        private final String course;
        public DetectThread(String classname, String course){
            this.classname = classname;
            this.course = course;
        }

        @Override
        public void run() {
            while (true){
                this.detect();
                if (Thread.currentThread().isInterrupted()){
                    break;
                }
            }
        }
        private void detect(){
            String saveState = scrfdNcnn.getSaveState();
            try {
                if (saveState.equals("success")){
                    presenter.fetch(this.classname, this.course);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void signFace(Map<String, Object> response) {
        if (response != null){
            System.out.println("set info");
            String uname = (String) response.get("name");
            String id = (String) response.get("uno");
            Bitmap bitmap = ImageUtils.bytes2bitmap(Base64.getDecoder().decode((String)response.get("face")));
            avatar.setImageBitmap(bitmap);
            showSignMessage(uname, id);
        }
    }

    @Override
    public void getDetail(Map<String, Object> response) {
        System.out.println(response);
        if (response != null){
            // 未签到
            List<CustomRecycleViewAdapter.Data> list1 = new ArrayList<>();
            // 已签到
            List<CustomRecycleViewAdapter.Data> list2 = new ArrayList<>();
            Set<String> strings = response.keySet();
            for (String s:strings){
                Number s1 = (Number)response.get(s);
                if (s1 != null){
                    byte state = s1.byteValue();
                    if (state == 0){
                        list1.add(new CustomRecycleViewAdapter.Data(s));
                    }else {
                        list2.add(new CustomRecycleViewAdapter.Data(s));
                    }
                }else {
                    Toast.makeText(this, "出错了QVQ", Toast.LENGTH_SHORT).show();
                }
            }
            DialogX.globalStyle = MaterialStyle.style();
            BottomDialog.build()
                    .setCustomView(new OnBindView<BottomDialog>(R.layout.sign_detail) {
                        @Override
                        public void onBind(BottomDialog dialog, View v) {
                            recyclerView1 = v.findViewById(R.id.unsign_detail_show);
                            recyclerView2 = v.findViewById(R.id.sign_detail_show);
                            recyclerView1.setLayoutManager(new LinearLayoutManager(v.getContext()));
                            recyclerView2.setLayoutManager(new LinearLayoutManager(v.getContext()));
                            recyclerView1.setAdapter(new CustomRecycleViewAdapter(list1).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                // TODO: 手动签到
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    MessageDialog.build()
                                            .setStyle(IOSStyle.style())
                                            .setTitle("手动签到")
                                            .setMessage("确定将"+list1.get(i).getText().split("\t")[1]+"设置为签到状态？")
                                            .setCancelButton("取消")
                                            .setOkButton("确定", new OnDialogButtonClickListener<MessageDialog>() {
                                                @Override
                                                public boolean onClick(MessageDialog dialog, View v) {
                                                    presenter.setSign(list1.get(i).getText().split("\t")[0], 1);
                                                    return false;
                                                }
                                            }).show();
                                }
                            }));
                            recyclerView2.setAdapter(new CustomRecycleViewAdapter(list2).setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                // TODO: 手动签到
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    MessageDialog.build()
                                            .setStyle(IOSStyle.style())
                                            .setTitle("手动签到")
                                            .setMessage("确定将"+list2.get(i).getText().split("\t")[1]+"设置为未签到状态？")
                                            .setCancelButton("取消")
                                            .setOkButton("确定", new OnDialogButtonClickListener<MessageDialog>() {
                                                @Override
                                                public boolean onClick(MessageDialog dialog, View v) {
                                                    presenter.setSign(list2.get(i).getText().split("\t")[0], 0);
                                                    return false;
                                                }
                                            }).show();
                                }
                            }));
                        }
                    }).show();
        }
    }

    @Override
    public void setSign(Object response) {
        if (response != null){
            String res = (String) response;
            if (res.equals("success")){
                Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "设置失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void endSign(Object response) {
        if (response != null){
            if (((String)response).equals("success"))
            Toast.makeText(this, "已结束签到", Toast.LENGTH_SHORT).show();
            System.exit(0);
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
        btn2 = findViewById(R.id.sign_detail);
        btn3 = findViewById(R.id.sign_end);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int new_facing = 1 - facing;
                scrfdNcnn.closeCamera();
                scrfdNcnn.openCamera(new_facing);
                facing = new_facing;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getDetail(classname);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageDialog.show("结束签到", "您确定要结束签到吗?", "确定", "取消").setOkButton(new OnDialogButtonClickListener<MessageDialog>() {
                    @Override
                    public boolean onClick(MessageDialog baseDialog, View v) {
                        presenter.endSign(classname);
                        return false;
                    }
                });
            }
        });

        scrfdNcnn.loadModel(getAssets(), true);
    }
}