package com.example.freetime.model;

import android.graphics.Bitmap;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.ISuperResolveModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.SuperResolveService;
import com.example.freetime.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

public class SuperResolveModel implements ISuperResolveModel {

    private byte[] img;

    public SuperResolveModel(Bitmap img) throws IOException {
        if (img != null){
            this.img = ImageUtils.bitmap2Bytes(img);
        }
    }

    @Override
    public void enhance(OnLoaderListener onLoaderListener) {
        onLoaderListener.onObjectComplete(enhance());
    }

    private byte[] enhance(){
        List<byte[]> list = new ArrayList<>();
        if (img != null){
            SuperResolveService service = RetrofitClient.getInstance().getService(SuperResolveService.class);
            service.enhance(this.img).subscribe(new Consumer<ResponseBean<byte[]>>() {
                @Override
                public void accept(ResponseBean<byte[]> responseBean) throws Throwable {
                    list.add(responseBean.getData());
                }
            });
        }
        return list.get(0);
    }
}
