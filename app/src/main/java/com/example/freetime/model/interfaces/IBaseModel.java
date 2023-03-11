package com.example.freetime.model.interfaces;

import java.util.List;
import java.util.Map;

public interface IBaseModel {
    interface OnLoaderListener{
        void onMapComplete(Map<String, Object> map);
        void onListComplete(List<Object> list);
        void onObjectComplete(Object obj);
        void onErrMsg(String msg);
    }
}
