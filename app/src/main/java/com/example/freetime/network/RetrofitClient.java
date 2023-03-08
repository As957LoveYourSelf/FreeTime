package com.example.freetime.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.freetime.BaseActivity;
import com.example.freetime.utils.SaveInfoUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient{
    private static volatile RetrofitClient mInstance;
    private Retrofit retrofit;
    private static String BASE_URL = "http://10.0.2.2:8066/";
    private RetrofitClient(){

    }

    public static RetrofitClient getInstance() {
        if (mInstance == null){
            synchronized (RetrofitClient.class){
                if (mInstance == null){
                    mInstance = new RetrofitClient();
                }
            }
        }
        return mInstance;
    }

    public <T> T getService(Class<T> cls){
        return getRetrofit().create(cls);
    }

    private synchronized Retrofit getRetrofit(){
        if (retrofit == null){
            String[] strings = SaveInfoUtils.readInfo();
            OkHttpClient httpClient = new OkHttpClient.Builder()
                    // 设置拦截器，添加统一的请求头
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request = chain.request().newBuilder()
                                    .addHeader("Authorization", strings[1])
                                    .build();
                            // 开始请求
                            return chain.proceed(request);
                        }
                    }).
                    connectTimeout(60, TimeUnit.SECONDS).
                    readTimeout(60, TimeUnit.SECONDS).
                    writeTimeout(60, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
