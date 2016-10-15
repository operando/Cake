package com.os.operando.cake.util;

import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.widget.RemoteViews;

public final class RemoteViewsUtil {

    private RemoteViewsUtil() {
    }

    public static void setBackground(RemoteViews remoteViews, @IdRes int viewId, @ColorInt int backgroundColor) {
        remoteViews.setInt(viewId, "setBackgroundColor", backgroundColor);
    }
}