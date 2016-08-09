package com.speedsumm.bu.sqldb1;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * Created by bu on 07.08.2016.
 */
public class MyWidget extends AppWidgetProvider{


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int i = 0;i<appWidgetIds.length;++i){
            RemoteViews remoteViews = updateWidgetListView(context,appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i],remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget_layout);
        Intent intent  = new Intent(context,WidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(appWidgetId,R.id.listViewWidget,intent);

        return remoteViews;
    }

    @Override
    public void onEnabled(Context context) {

        super.onEnabled(context);

    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }
}
