package com.speedsumm.bu.sqldb;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener{
    TextView headerText;
    Button btnNewTask;
    TaskDialog dialogFragment = new TaskDialog();
    DbHandler dbHandler = new DbHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerText = (TextView)findViewById(R.id.textView);
        btnNewTask = (Button) findViewById(R.id.btnNewTask);

        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogFragment.show(getSupportFragmentManager(),"Пробный диалог");
            }
        });




//        dbHandler.addTask(new Task(20160706,"Война с Англией"));
//        dbHandler.addTask(new Task(20160705,"Разгон облаков"));
//        dbHandler.addTask(new Task(20160706,"Празднование дня рождения erthertherthrth retherthertyh retyherth ert5uy  ertertyert yerrtytryertyertyer erertewrt wertwert ewrtewrtewrt wertertert "));


        ArrayList<Task> taskArrayList = dbHandler.getAllTasks();
        if (taskArrayList.size()!=0){
            headerText.setText("You have "+ taskArrayList.size()+ " tasks in your TaskList");
        }else{
            headerText.setText("Your TaskList is empty");
        }

        TaskAdapter taskAdapter = new TaskAdapter(this,taskArrayList);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(taskAdapter);

    }

    public void SetDate(View view) {
//        switch (view.getId()){
//            case(R.id.tv_taskDate):
//                DatePickDialog datePickerDialog = new DatePickDialog();
//                datePickerDialog.show(getSupportFragmentManager(),"DatePicker");
        //TODO не забыть восстановить DatePicker

//        }
    }





    @Override
    public void onDialogPositiveClick(AppCompatDialogFragment dialog, String taskName, String taskDate) {
        dbHandler.addTask(new Task(Integer.valueOf(taskDate),taskName));
    }

    @Override
    public void onDialogNegativeClick(AppCompatDialogFragment dialog) {

    }
}
