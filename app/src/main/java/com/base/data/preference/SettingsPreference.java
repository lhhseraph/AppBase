package com.base.data.preference;

import android.content.Context;

public class SettingsPreference {
    private static final String NAME = "SETTINGS";
    private static final String FIRST_START = "first_start";
    private static final String DEFAULT_ADDR_ID = "default_addr_id";
    private static final String LOGIN_NAME = "login_name";
    private static final String LOGIN_PWD = "login_pwd";

    public SettingsPreference() {
    }

    public static boolean getFirstStart(Context context) {
        return (new AppPreference(context, "SETTINGS")).getBoolean(FIRST_START, true);
    }

    public static void setFirstStart(Context context, boolean flag) {
        (new AppPreference(context, "SETTINGS")).save(FIRST_START, flag);
    }

    public static String getDefaultAddrId(Context context) {
        return (new AppPreference(context, "SETTINGS")).get(DEFAULT_ADDR_ID, null);
    }

    public static void setDefaultAddrId(Context context, String id) {
        (new AppPreference(context, "SETTINGS")).save(DEFAULT_ADDR_ID, id);
    }

    public static String getLoginName(Context context) {
        return (new AppPreference(context, "SETTINGS")).get(LOGIN_NAME, null);
    }

    public static void setLoginName(Context context, String id) {
        (new AppPreference(context, "SETTINGS")).save(LOGIN_NAME, id);
    }

    public static String getLoginPwd(Context context) {
        return (new AppPreference(context, "SETTINGS")).get(LOGIN_PWD, null);
    }

    public static void setLoginPwd(Context context, String id) {
        (new AppPreference(context, "SETTINGS")).save(LOGIN_PWD, id);
    }
}
