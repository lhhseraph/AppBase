package com.base.data.remote.http;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

/**
 * Created by Administrator on 2016/11/30.
 * https://github.com/hongyangAndroid/okhttputils
 */

public class HttpUtils {
    private static final String TAG = "HttpUtils";

    public static void init() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().
                connectTimeout(10000L, TimeUnit.MILLISECONDS).
                readTimeout(10000L, TimeUnit.MILLISECONDS).
                addInterceptor(new LoggerInterceptor("TAG")).build();
        OkHttpUtils.initClient(mOkHttpClient);
    }

    public void clearSession() {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }


}
