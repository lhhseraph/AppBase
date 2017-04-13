package com.base.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.base.utils.toast.ToastUtils;

/**
 * Created by Administrator on 2016/10/19.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToast(String msg) {
        ToastUtils.showShortToast(BaseActivity.this, msg);
    }
}
