package com.speedsumm.bu.sqldb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class TaskAdapter extends ArrayAdapter <Task>{
    public TaskAdapter(Context context, ArrayList<Task> taskArrayList) {
        super(context, 0, taskArrayList);




    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent) {
        final Task task= getItem(position);
        final DbHandler dbHandler = new DbHandler(getContext());

        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.task_list,parent,false);
//        final CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
//        final TextView taskNameText  = (TextView)convertView.findViewById(R.id.taskHeader);
//        Button btnDeleteTask = (Button)convertView.findViewById(R.id.btnDeleteTask);
//
//        View.OnClickListener onClickDialog = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()){
//                    case R.id.btnDeleteTask:
//                        remove(task);
//                        dbHandler.deleteTask(task.get_id());
//                        break;
//
//                    case R.id.checkBox:
//                        checkBox.setChecked(true);
//                        remove(task);
//                        dbHandler.updateTask(task.get_id());
//
//                        break;
//                }
//            }
//
//        };
//        checkBox.setOnClickListener(onClickDialog);
//        btnDeleteTask.setOnClickListener(onClickDialog);
//
//        if (task.get_completed()==1){
//            checkBox.setChecked(true);
//        }else{
//            checkBox.setChecked(false);
//        }
//
//        taskNameText.setText(task.get_taskName());

        return convertView;
    }



}
