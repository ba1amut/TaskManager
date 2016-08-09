package com.speedsumm.bu.sqldb1;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bu on 09.08.2016.
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private ArrayList<Task> taskArrayList = new ArrayList<Task>();
    private Context context;
    private int appWidgetId;


    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        taskArrayList = MainActivity.taskArrayList;

    }

    @Override
    public void onCreate() {


    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return taskArrayList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.y\nHH:mm");

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.task_list);
        Task task = taskArrayList.get(position);
        remoteViews.setTextViewText(R.id.taskName, task.get_taskName());
        remoteViews.setTextViewText(R.id.taskBody, task.get_taskBody());

        Date date = new Date(taskArrayList.get(position).get_expDate());
        String srtDateTime = simpleDateFormat.format(date);

        remoteViews.setTextViewText(R.id.taskDate, srtDateTime);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
