package com.example.freetime;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.presenter.StyleTransformPresenter;
import com.example.freetime.utils.SaveInfoUtils;
import com.example.freetime.view.IStyleTransformView;

public class StyleTransformActivity extends BaseActivity<StyleTransformPresenter, IStyleTransformView> implements IStyleTransformView {

    ImageView view;
    ImageButton trans_btn;
    ImageButton choose_style_btn;
    TextView textView;
    int type;
    private PopupWindow choosepop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style_transform);
        view = findViewById(R.id.style_tran_img_show);
        trans_btn = findViewById(R.id.trans_btn);
        choose_style_btn = findViewById(R.id.choose_style_btn);
        textView = findViewById(R.id.style_mode);
        type = 0;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow(R.id.style_tran_img_show);
            }
        });
        choose_style_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popueChooseWindow();
            }
        });
        trans_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasresult){
                    Toast.makeText(StyleTransformActivity.this, "正在后台转化中，请稍等", Toast.LENGTH_SHORT).show();
                    presenter.defaultStyleTransform(((BitmapDrawable)view.getDrawable()).getBitmap(),type, SaveInfoUtils.readInfo()[0]);
                }
            }
        });
    }

    @Override
    protected StyleTransformPresenter createPresenter() {
        return new StyleTransformPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }


    @Override
    public void defaultStyleTransform(byte[] result) {
        if (result != null){
            view.setImageBitmap(BitmapFactory.decodeByteArray(result,0,result.length));
            Toast.makeText(StyleTransformActivity.this, "转化成功!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void anyStyleTransform(byte[] result) {
        if (result != null){

        }
    }

    private void popueChooseWindow(){
        View bottomView = View.inflate(StyleTransformActivity.this, R.layout.style_choos, null);
        Button btn1 = bottomView.findViewById(R.id.btn_type0);
        Button btn2 = bottomView.findViewById(R.id.btn_type1);
        Button btn3 = bottomView.findViewById(R.id.btn_type2);
        Button mCancel = bottomView.findViewById(R.id.style_tran_channel);

        choosepop = new PopupWindow(bottomView, -1, -2);
        choosepop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        choosepop.setOutsideTouchable(true);
        choosepop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        choosepop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        choosepop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_type0:
                        type = 0;
                        textView.setText(btn1.getText());
                        Toast.makeText(StyleTransformActivity.this, "已选择："+btn1.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_type1:
                        type = 1;
                        textView.setText(btn2.getText());
                        Toast.makeText(StyleTransformActivity.this, "已选择："+btn2.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_type2:
                        type = 2;
                        textView.setText(btn3.getText());
                        Toast.makeText(StyleTransformActivity.this, "已选择："+btn3.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.style_tran_channel:
                        closeChoosePopupWindow();
                        break;
                }
                closeChoosePopupWindow();
            }
        };
        btn1.setOnClickListener(clickListener);
        btn2.setOnClickListener(clickListener);
        btn3.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
    }

    private void closeChoosePopupWindow() {
        if (choosepop != null && choosepop.isShowing()) {
            choosepop.dismiss();
            choosepop = null;
        }
    }
}