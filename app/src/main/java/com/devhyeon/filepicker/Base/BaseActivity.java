package com.devhyeon.filepicker.Base;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created By DevHyeon on 2020.11.21
 * DevHyeon Blog : https://www.devhyeon.com
 * DevHyeon Git  : https://github.com/DevHyeon0312
 * BaseActivity ( ViewBinding )
 * */
public abstract class BaseActivity<T> extends AppCompatActivity {
    private final String TAG = BaseActivity.class.getSimpleName();

    protected T xml = null;
    protected Activity mActivity;
    protected Context mContext;
    private View root;

    protected abstract void setBindingView(@NonNull View view);

    protected void setContentsView(@NonNull View view, @NonNull T binding) {
        super.setContentView(view);
        this.xml = binding;
        this.mActivity = this;
        this.mContext = this;
        this.root = view;
    }

    protected View getView(@LayoutRes int layoutId) {
        return this.getLayoutInflater().inflate(layoutId, null);
    }

    protected View getRoot() {
        return this.root;
    }
}