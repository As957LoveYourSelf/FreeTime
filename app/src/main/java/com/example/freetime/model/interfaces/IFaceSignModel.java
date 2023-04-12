package com.example.freetime.model.interfaces;

public interface IFaceSignModel extends IBaseModel{
    void signFace(OnLoaderListener onLoaderListener);
    void getDetail(OnLoaderListener onLoaderListener);
    void setSign(OnLoaderListener onLoaderListener);
    void endSign(OnLoaderListener onLoaderListener);
}
