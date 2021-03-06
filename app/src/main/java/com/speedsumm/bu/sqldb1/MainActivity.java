package com.speedsumm.bu.sqldb1;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    public static DbHandler dbHandler;
    public static String colorCompleted;
    public static String colorDelete;
    public static Resources res;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    public static ArrayList<Task> taskArrayList;
    TaskProvider taskProvider;
    static Handler mHandler;
    static int countNotify = 1;
    static Context context;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHandler = new DbHandler(this);
        context  = MainActivity.this;



        taskArrayList = new ArrayList<>();
        taskArrayList = dbHandler.getAllOpenTask(taskArrayList);
        colorCompleted = getResources().getString(R.color.completedTask);
        colorDelete = getResources().getString(R.color.deleteTask);
        res = getResources();
        taskProvider = new TaskProvider();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Toast.makeText(getBaseContext(), "Время настало", Toast.LENGTH_SHORT).show();
                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(R.drawable.ic_delete_white_48dp)
                        .setContentTitle("Проосрочено выполнение")
                        .setContentText(msg.obj.toString());
//                Intent resultIntent = new Intent(MainActivity.this,MainActivity.class);
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
//                stackBuilder.addParentStack(MainActivity.class);
                NotificationManager notificationManager = (NotificationManager)getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
                notificationManager.notify(countNotify++,mBuilder.build());

            }
        };

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab1);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTask.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        Cursor cursor = dbHandler.DbAllTask();
        while (cursor.moveToNext()) {
            String TASK_NAME = cursor.getString(cursor.getColumnIndex("N_TASK"));
            String TASK_BODY = cursor.getString(cursor.getColumnIndex("B_TASK"));
            Log.d(".....", "NAme " + TASK_NAME + ", Body " + TASK_BODY);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mAdapter = new MyAdapter(taskArrayList);
        mRecyclerView.setAdapter(mAdapter);


        Drawable dividerDrawable = getDrawable(android.R.drawable.divider_horizontal_bright);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(dividerDrawable);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            dbHandler.addTask(new Task(0, data.getStringExtra("taskName"), data.getStringExtra("taskBody"), data.getLongExtra("expDate",0)));
            taskArrayList = dbHandler.getAllOpenTask(taskArrayList);

            mAdapter.notifyDataSetChanged();

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.CompleteTask:
                if (dbHandler.getTasksListFlag() == 0) {
                    item.setTitle("Открытые задачи");
                    dbHandler.setTasksListFlag(1);
                    taskArrayList = dbHandler.getAllOpenTask(taskArrayList);
                    mAdapter.notifyDataSetChanged();
                } else {
                    item.setTitle("Закрытые задачи");
                    dbHandler.setTasksListFlag(0);
                    taskArrayList = dbHandler.getAllOpenTask(taskArrayList);
                    mAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.SaveData:
                SaveTasks();
                break;
            case R.id.deleteCompTask:
                dbHandler.deleteCompletedTask();
                if ( dbHandler.getTasksListFlag()==1){
                    taskArrayList = dbHandler.getAllOpenTask(taskArrayList);
                }
                mAdapter.notifyDataSetChanged();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void SaveTasks() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("DBbackup.txt", Context.MODE_PRIVATE);
            ArrayList<Task> taskArrayList = dbHandler.getAllTask();
            for (int i = 0; i < taskArrayList.size(); i++) {
                fileOutputStream.write((taskArrayList.get(i).get_id() + "," + taskArrayList.get(i).get_taskName() + "," + taskArrayList.get(i).get_completed() + "\n").getBytes());
            }
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

