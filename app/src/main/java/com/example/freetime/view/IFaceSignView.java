package com.example.freetime.view;


import java.util.Map;

public interface IFaceSignView extends IBaseView{
    void signFace(Map<String, Object> response);
    void getDetail(Map<String, Object> response);
    void setSign(Object response);
    void endSign(Object response);
}
