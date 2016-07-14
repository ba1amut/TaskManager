package com.speedsumm.bu.sqldb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
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
        dbHandler.addTask(new Task(0,"1111","22222","33333"));
        dbHandler.addTask(new Task(0,"4","5","6"));
        dbHandler.addTask(new Task(0,"7","8","9"));
        dbHandler.addTask(new Task(0,"10","11","13"));

//        dbHandler.setTasksListFlag(0);
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        headerText = (TextView) findViewById(R.id.textView);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, NewTask.class);
//                startActivityForResult(intent, REQUEST_CODE);
//
//            }
//        });

//        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
//        mRecyclerView.setHasFixedSize(true);
//
//        mlayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mlayoutManager);
//



    }
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            if (resultCode== RESULT_OK && data !=null){
//                dbHandler.addTask(new Task(0,data.getStringExtra("taskName"),data.getStringExtra("taskBody"),data.getStringExtra("expDate")));
//            }
//        }




//        taskListUpdate();

//    public void taskListUpdate() {
//        ArrayList<Task> taskArrayList = dbHandler.getAllOpenTask();
//        TaskAdapter taskAdapter = new TaskAdapter(this,taskArrayList);
//        ListView listView = (ListView)findViewById(R.id.listView);
//        listView.setAdapter(taskAdapter);
//        if (taskArrayList.size()!=0){
//            headerText.setText("You have "+ taskArrayList.size()+ " tasks in your TaskList");
//        }else{
//            headerText.setText("Your TaskList is empty");
//        }
//    }
//    @Override
//    public void onDialogPositiveClick(AppCompatDialogFragment dialog, String taskName) {
//        dbHandler.addTask(new Task(0,taskName));
//        taskListUpdate();
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.CompleteTask:
//                if(dbHandler.getTasksListFlag() == 0) {
//                    item.setTitle("Открытые задачи");
//                    dbHandler.setTasksListFlag(1);
//                    taskListUpdate();
//                }else{
//                    item.setTitle("Закрытые задачи");
//                    dbHandler.setTasksListFlag(0);
//                    taskListUpdate();
//                }
//                break;
//            case R.id.SaveData:
//                SaveTasks ();
//        }
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    private void SaveTasks() {
//        try {
//            FileOutputStream fileOutputStream = openFileOutput("DBbackup.txt", Context.MODE_PRIVATE);
//            ArrayList<Task> taskArrayList = dbHandler.getAllTask();
//            for (int i=0;i<taskArrayList.size();i++) {
//                fileOutputStream.write((taskArrayList.get(i).get_id()+"," + taskArrayList.get(i).get_taskName()+","+taskArrayList.get(i).get_completed()+"\n").getBytes());
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
