package com.speedsumm.bu.sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by bu on 06.07.2016.
 */
public class DbHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "TASK_DB";
    private static final String TABLE_NAME = "TASKS";
    private static final String ID_KEY = "ID";
    private static final String DATE_KEY = "DATE_TASK";
    private static final String NAME_KEY = "NAME_TASK";

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID_KEY + " INTEGER PRIMARY KEY, " + DATE_KEY + " TEXT, " + NAME_KEY + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TASK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NAME );
    }

    public void addTask(Task task) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE_KEY, task.get_dateTask());
        contentValues.put(NAME_KEY, task.get_taskName());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME  + " ORDER BY " + DATE_KEY + " DESC", null);

        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Task task = new Task();
            task.set_id(cursor.getInt(cursor.getColumnIndex(ID_KEY)));
            task.set_dateTask(cursor.getInt(cursor.getColumnIndex(DATE_KEY)));
            task.set_taskName(cursor.getString(cursor.getColumnIndex(NAME_KEY)));
            taskArrayList.add(task);
        }
        cursor.close();
        return taskArrayList;
    }




}
