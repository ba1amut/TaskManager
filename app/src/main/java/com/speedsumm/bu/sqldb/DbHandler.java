package com.speedsumm.bu.sqldb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DbHandler extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "TASK_DB";
    private static final String TABLE_NAME = "TASKS";
    private static final String ID_KEY = "ID";
    private static final String COMPLETED_KEY = "COMPLETE_KEY";
    private static final String NAME_KEY = "NAME_TASK";
    private static final String OpenTaskList = "SELECT * FROM " + TABLE_NAME + " WHERE " + COMPLETED_KEY + " IS NOT 1";
    private static final String CloseTaskList = "SELECT * FROM " + TABLE_NAME + " WHERE " + COMPLETED_KEY + " IS NOT 0";
    private String sql;
    private int tasksListFlag = 0;


    public int getTasksListFlag() {
        return tasksListFlag;
    }

    public void setTasksListFlag(int tasksListFlag) {
        this.tasksListFlag = tasksListFlag;
    }

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID_KEY + " INTEGER PRIMARY KEY, " + COMPLETED_KEY + " INTEGER DEFAULT 0, " + NAME_KEY + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TASK_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
    }

    public void addTask(Task task) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COMPLETED_KEY, task.get_completed());
        contentValues.put(NAME_KEY, task.get_taskName());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Task> getAllOpenTask() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        if (tasksListFlag == 0) {
            sql = OpenTaskList;
        } else if (tasksListFlag == 1) {
            sql = CloseTaskList;
        }
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.set_id(cursor.getInt(cursor.getColumnIndex(ID_KEY)));
            task.set_completed(cursor.getInt(cursor.getColumnIndex(COMPLETED_KEY)));
            task.set_taskName(cursor.getString(cursor.getColumnIndex(NAME_KEY)));
            taskArrayList.add(task);
        }
        cursor.close();
        return taskArrayList;
    }

    public void deleteTask(int taskID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ID_KEY + " = " + taskID);
        sqLiteDatabase.close();
    }

    public void updateTask(int taskID) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("UPDATE " + TABLE_NAME + " SET " + COMPLETED_KEY + " = 1 WHERE " + ID_KEY + " = " + taskID);
    }

    public ArrayList<Task> getAllTask() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sql = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.set_id(cursor.getInt(cursor.getColumnIndex(ID_KEY)));
            task.set_completed(cursor.getInt(cursor.getColumnIndex(COMPLETED_KEY)));
            task.set_taskName(cursor.getString(cursor.getColumnIndex(NAME_KEY)));
            taskArrayList.add(task);
        }
        cursor.close();
        return taskArrayList;
    }

}
