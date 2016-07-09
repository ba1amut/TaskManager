package com.speedsumm.bu.sqldb;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener{
    TextView headerText;
    TaskDialog dialogFragment = new TaskDialog();
    DbHandler dbHandler = new DbHandler(this);





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity);

        dbHandler.setTasksListFlag(0);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        headerText = (TextView)findViewById(R.id.textView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogFragment.show(getSupportFragmentManager(),"newTaskDialog");
            }
        });
        taskListUpdate();
    }
    public void taskListUpdate() {
        ArrayList<Task> taskArrayList = dbHandler.getAllOpenTask();
        if (taskArrayList.size()!=0){
            headerText.setText("You have "+ taskArrayList.size()+ " tasks in your TaskList");
        }else{
            headerText.setText("Your TaskList is empty");
        }
        TaskAdapter taskAdapter = new TaskAdapter(this,taskArrayList);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(taskAdapter);
    }
    @Override
    public void onDialogPositiveClick(AppCompatDialogFragment dialog, String taskName) {
        dbHandler.addTask(new Task(0,taskName));
        taskListUpdate();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.CompleteTask:
                if(dbHandler.getTasksListFlag() == 0) {
                    item.setTitle("Открытые задачи");
                    dbHandler.setTasksListFlag(1);
                    taskListUpdate();
                }else{
                    item.setTitle("Закрытые задачи");
                    dbHandler.setTasksListFlag(0);
                    taskListUpdate();
                }
                break;
            case R.id.SaveData:
                SaveTasks ();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void SaveTasks() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("DBbackup.txt", Context.MODE_PRIVATE);
            ArrayList<Task> taskArrayList = dbHandler.getAllTask();
            for (int i=0;i<taskArrayList.size();i++) {
                fileOutputStream.write((taskArrayList.get(i).get_id()+"," + taskArrayList.get(i).get_taskName()+","+taskArrayList.get(i).get_completed()+"\n").getBytes());
            }
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
