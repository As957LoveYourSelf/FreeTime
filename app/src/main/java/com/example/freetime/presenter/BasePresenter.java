package com.example.freetime.presenter;
import com.example.freetime.view.IBaseView;
import java.lang.ref.WeakReference;

/**
 * P层持有 View 和 Model，用于处理业务逻辑
 */

public class BasePresenter<T extends IBaseView> implements IBasePresenter{
    /**
     * 避免内存泄露
     */
    WeakReference<T> mView;

    /**
     * 绑定
     */
    public void attachView(T view){
        mView = new WeakReference<>(view);
    }

    /**
     * 解绑定
     */
    public void detachView(){
        if (mView != null){
            mView.clear();
            mView = null;
        }
    }

}
