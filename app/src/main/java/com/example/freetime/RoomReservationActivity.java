package com.example.freetime;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freetime.utils.SaveInfoUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoomReservationActivity extends AppCompatActivity {


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_reservation);
        WebView webView = findViewById(R.id.webview);
        Map<String,String> params = new HashMap<>();
        params.put("uid", SaveInfoUtils.readInfo()[0]);
        params.put("token", SaveInfoUtils.readInfo()[1]);
        WebSettings webSettings = webView.getSettings();
        webSettings.setDomStorageEnabled(true);//主要是这句
        webSettings.setJavaScriptEnabled(true);//启用js
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        //该方法解决的问题是打开浏览器不调用系统浏览器，直接用webview打开

        try {
            System.out.println(concatParams(params));
            webView.loadUrl("http://192.168.0.4:5173/webview?"+concatParams(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private String concatParams(Map<String,String> params) throws UnsupportedEncodingException {
        if(params.size() ==0){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            String value = URLEncoder.encode(params.get(key), "UTF-8");
            builder.append(String.format("%s=%s&", key, value));
        }
        builder.deleteCharAt(builder.lastIndexOf("&"));
        return builder.toString();
    }
}