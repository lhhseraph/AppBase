package com.base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.base.BuildConfig;
import com.base.utils.log.KLog;
import com.base.utils.toast.ToastUtils;

import me.yokeyword.fragmentation.SupportFragment;


public class BaseFragment extends SupportFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    boolean isStoped = false;

    public boolean isFinished() {
        if (isStoped) return true;
        else return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isStoped = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        isStoped = true;
    }

    public void showToast(final String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public void showToast(final String msg, int duration) {
        if (isFinished()) {
            return;
        }
        if (duration != Toast.LENGTH_SHORT && duration != Toast.LENGTH_LONG) {
            throw new IllegalArgumentException("showTime must be Toast.LENGTH_SHORT or Toast.LENGTH_SHORT");
        }
        ToastUtils.showShortToast(getActivity(), msg);
    }


    public void showDebugToast(final String msg) {
        if (BuildConfig.DEBUG) {
            showToast(msg, Toast.LENGTH_LONG);
        }
    }


    public void finish() {
        pop();
    }


    /**
     * 一个Activity 里 ViewPage（或其他容器）与多个Fragments使用时可见变化时响应
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        KLog.e("setUserVisibleHint");
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
            onResume();
        } else {
            //相当于Fragment的onPause
            onPause();
        }
    }

    /**
     * Activity 里 Fragments切换时响应，是否可见
     *
     * @param hidd
     */
    @Override
    public void onHiddenChanged(boolean hidd) {
        KLog.e("onHiddenChanged");
        super.onHiddenChanged(hidd);
        if (hidd) {
            //相当于Fragment的onPause
            onPause();
        } else {
            //相当于Fragment的onResume
            onResume();
        }
    }
}
