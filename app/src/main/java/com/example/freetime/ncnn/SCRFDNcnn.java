package com.example.freetime.ncnn;

import android.content.res.AssetManager;
import android.view.Surface;

public class SCRFDNcnn {
    public native boolean loadModel(AssetManager mgr);
    public native boolean openCamera(int facing);
    public native boolean closeCamera();
    public native boolean setOutputWindow(Surface surface);
    public native void cropFace();
    public native String getSaveState();

    static {
        System.loadLibrary("freetime");
    }
}
