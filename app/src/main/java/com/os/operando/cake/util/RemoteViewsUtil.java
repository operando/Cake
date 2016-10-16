package com.os.operando.cake.util;

import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.util.TypedValue;
import android.widget.RemoteViews;

public final class RemoteViewsUtil {

    private RemoteViewsUtil() {
    }

    public static void setBackground(RemoteViews remoteViews, @IdRes int viewId, @ColorInt int backgroundColor) {
        remoteViews.setInt(viewId, "setBackgroundColor", backgroundColor);
    }

    public static void setTextSize(RemoteViews remoteViews, @IdRes int viewId, float textSize) {
        remoteViews.setTextViewTextSize(viewId, TypedValue.COMPLEX_UNIT_SP, textSize);
    }
}