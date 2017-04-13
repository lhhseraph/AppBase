package com.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.base.utils.toast.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;


public class BaseFragment extends SupportFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void showToast(final String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public void showToast(final String msg, int duration) {
        if (duration != Toast.LENGTH_SHORT && duration != Toast.LENGTH_LONG) {
            throw new IllegalArgumentException("showTime must be Toast.LENGTH_SHORT or Toast.LENGTH_SHORT");
        }
        ToastUtils.showShortToast(getActivity(), msg);
    }

    public void finish() {
        pop();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }

}
