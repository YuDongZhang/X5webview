package com.example.test_webview_demo.utils;

public class LogUtil {
    public static final String APPTAG = "x5x5x5";
    public static final boolean IS_PRINT_LOG = true;

    public LogUtil() {
    }

    public static void v(String tag, String msg) {
        android.util.Log.v(APPTAG, "------" + tag + "---------" + msg);
    }

    public static void d(String tag, String msg) {
        android.util.Log.v(APPTAG, "------" + tag + "---------" + msg);
    }

    public static void i(String tag, String msg) {
        android.util.Log.i(APPTAG, "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(APPTAG, "------" + tag + "---------" + msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        android.util.Log.v("TotalLearn", "------" + tag + "---------" + msg);
    }
}