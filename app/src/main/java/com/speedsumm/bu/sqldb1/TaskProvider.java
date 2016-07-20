package com.speedsumm.bu.sqldb1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by bu on 20.07.2016.
 */
public class TaskProvider extends ContentProvider {
    public static final String PROVIDER_NAME  = "com.speedsumm.bu";
    private static final Uri TASK_URI =Uri.parse("content://"+PROVIDER_NAME+"/tasks");
    private static final int GET_ALL_TASKS = 1;
    private static final int GET_ONE_TASK = 2;
    private static final int GET_COMP_TASK = 3;

    private static UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"tasks",GET_ALL_TASKS);
        uriMatcher.addURI(PROVIDER_NAME,"tasks/#",GET_ONE_TASK);
        uriMatcher.addURI(PROVIDER_NAME,"tasks/comp",GET_COMP_TASK);

    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        Cursor cursor;
        switch (uriMatcher.match(uri)){
            case GET_ALL_TASKS:
                cursor = MainActivity.dbHandler.DbAllTask();
                cursor.setNotificationUri(getContext().getContentResolver(),uri);
                return cursor;
            case GET_ONE_TASK:

                cursor = MainActivity.dbHandler.DbOneTask(uri.getPathSegments().get(1));
                cursor.setNotificationUri(getContext().getContentResolver(),uri);
                return cursor;
            case GET_COMP_TASK:
                cursor = MainActivity.dbHandler.DbCompTask();
                cursor.setNotificationUri(getContext().getContentResolver(),uri);
                return cursor;


        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        long row = MainActivity.dbHandler.DbInsertTask(contentValues);
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
