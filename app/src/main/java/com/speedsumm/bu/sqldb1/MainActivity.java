package com.speedsumm.bu.sqldb1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    TextView headerText;
    DbHandler dbHandler;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;
    public ArrayList<Task> taskArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity);
        dbHandler = new DbHandler(this);

       taskArrayList = dbHandler.getAllTask();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);



        headerText = (TextView) findViewById(R.id.textView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTask.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mlayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mlayoutManager);

        mAdapter = new MyAdapter(taskArrayList);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            dbHandler.addTask(new Task(0, data.getStringExtra("taskName"), data.getStringExtra("taskBody"), data.getStringExtra("expDate")));
            taskArrayList.add(new Task(0, data.getStringExtra("taskName"), data.getStringExtra("taskBody"), data.getStringExtra("expDate")));

            mAdapter.notifyDataSetChanged();

        }
    }






//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.CompleteTask:
//                if (dbHandler.getTasksListFlag() == 0) {
//                    item.setTitle("Открытые задачи");
//                    dbHandler.setTasksListFlag(1);
//                    taskListUpdate();
//                } else {
//                    item.setTitle("Закрытые задачи");
//                    dbHandler.setTasksListFlag(0);
//                    taskListUpdate();
//                }
//                break;
//            case R.id.SaveData:
//                SaveTasks();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

//    private void SaveTasks() {
//        try {
//            FileOutputStream fileOutputStream = openFileOutput("DBbackup.txt", Context.MODE_PRIVATE);
//            ArrayList<Task> taskArrayList = dbHandler.getAllTask();
//            for (int i = 0; i < taskArrayList.size(); i++) {
//                fileOutputStream.write((taskArrayList.get(i).get_id() + "," + taskArrayList.get(i).get_taskName() + "," + taskArrayList.get(i).get_completed() + "\n").getBytes());
//            }
//            fileOutputStream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}

