package com.os.operando.cake.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.os.operando.cake.R;
import com.os.operando.cake.activity.MemoSummaryEditActivity;
import com.os.operando.cake.util.RemoteViewsUtil;

public class MemoAppWidget extends AppWidgetProvider {

    private static final String TAG = MemoAppWidget.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG, intent.getAction());
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId,
                                       String memo, int backgroundColor, float textSize, int textColor, long id) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_memo);
        views.setTextViewText(R.id.memo, memo);
        views.setTextColor(R.id.memo, textColor);
        RemoteViewsUtil.setBackground(views, R.id.memo, backgroundColor);
        RemoteViewsUtil.setTextSize(views, R.id.memo, textSize);

        Intent intent = MemoSummaryEditActivity.createIntent(context, id);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.memo_root, pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}

