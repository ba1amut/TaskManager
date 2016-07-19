package com.speedsumm.bu.sqldb1;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by bu on 18.07.2016.
 */
public class TaskProvider extends ContentProvider {
    private static final String  AUTHORITY = "com.speedsumm.bu.sqldb1.TaskProvider";
    static final String TASK_PATH = "TASKS";
    static final Uri  TASK_CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TASK_PATH);
    static final String TASK_CONTENT_TYPE = "vnd.android.cursor.dir/"+AUTHORITY+"."+TASK_PATH;
    static final String TASK_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/"+AUTHORITY+"."+TASK_PATH;

    static final int URI_TASK = 1;
    static final int URI_TASK_ID = 2;

    private static UriMatcher mURI_MATCHER = null;

    static {
        mURI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        mURI_MATCHER.addURI(AUTHORITY,TASK_PATH,URI_TASK);
        mURI_MATCHER.addURI(AUTHORITY,TASK_PATH+"/#",URI_TASK_ID);
    }

    DbHandler dbHandler;
    SQLiteDatabase sqLiteDatabase;



    @Override
    public boolean onCreate() {
        dbHandler = new DbHandler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        sqLiteDatabase = dbHandler.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TASK_PATH,strings,s,strings1,null,null,s1);
        cursor.setNotificationUri(getContext().getContentResolver(),TASK_CONTENT_URI);
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (mURI_MATCHER.match(uri)){
            case URI_TASK:
                return TASK_CONTENT_TYPE;
            case URI_TASK_ID:
                return TASK_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
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
