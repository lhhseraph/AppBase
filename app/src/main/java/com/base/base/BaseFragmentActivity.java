package com.base.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.base.BuildConfig;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;


public class BaseFragmentActivity extends SupportActivity {

    LocalBroadcastManager mLocalBroadcastManager;
    private final String EXIT_ACTION = "EXIT_APP";

    public boolean isStoped() {
        return isStoped;
    }

    public boolean isFinished() {
        if (isStoped || BaseFragmentActivity.this.isFinishing()) return true;
        else return false;
    }

    boolean isStoped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isStoped = false;

        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 处理界面锁定问题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams
                .FLAG_KEEP_SCREEN_ON);
        // 处理界面锁定问题


        IntentFilter filter = new IntentFilter();
        filter.addAction(EXIT_ACTION);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(mExitReceiver, filter);
    }

    BroadcastReceiver mExitReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(EXIT_ACTION)) {
                BaseFragmentActivity.this.finish();
            }
        }
    };

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mExitReceiver);
    }

    public void setAllowFastClick(boolean mAllowFastClick) {
        this.mAllowFastClick = mAllowFastClick;
    }

    public boolean mAllowFastClick = false;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (!mAllowFastClick && isFastDoubleClick()) {
                return true;
            }
        } else if (ev.getAction() == MotionEvent.ACTION_UP) {

        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
        }
        return super.dispatchTouchEvent(ev);
    }

    protected long lastClickTime;

    /**
     * 判断是否短时间多次点击，默认值1秒
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= 300 ? true : false;
    }

    public void sendExit() {
        Intent intent = new Intent("exit_app");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private int duration = Toast.LENGTH_SHORT;
    public Toast mToast;

    public void showToast(final String msg) {
        if (isFinished()) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mToast = Toast.makeText(getApplicationContext(), msg, duration);
                mToast.show();
            }
        });
    }

    public void showToast(final String msg, boolean flage) {
        if (flage) {
            duration = Toast.LENGTH_LONG;
        }
        showToast(msg);
    }

    public void showDebugToast(final String msg) {
        duration = Toast.LENGTH_LONG;
        if (BuildConfig.DEBUG) {
            showToast(msg);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        isStoped = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mToast != null) {
            mToast.cancel();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStoped = true;
    }


    @Override
    public void start(SupportFragment toFragment) {
        if (isStoped()) {
            return;
        }
        super.start(toFragment);
    }

    @Override
    public void startWithPop(SupportFragment toFragment) {
        if (isStoped()) {
            return;
        }
        super.startWithPop(toFragment);
    }

    public void start(final SupportFragment toFragment, @SupportFragment.LaunchMode final int launchMode) {
        if (isStoped()) {
            return;
        }
        super.start(toFragment, launchMode);
    }

    public void popTo(Class<?> fragmentClass, boolean includeSelf) {
        if (isStoped()) {
            return;
        }
        super.popTo(fragmentClass, includeSelf);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment f = getTopFragment();
        f.onActivityResult(requestCode, resultCode, data);
    }

}
