package com.speedsumm.bu.sqldb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bu on 06.07.2016.
 */
public class TaskAdapter extends ArrayAdapter <Task>{
    public TaskAdapter(Context context, ArrayList<Task> taskArrayList) {
        super(context, 0, taskArrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task= getItem(position);
        LayoutInflater inflater=(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.task_list,parent,false);
        TextView taskDateText = (TextView)convertView.findViewById(R.id.taskDate);
        TextView taskNameText  = (TextView)convertView.findViewById(R.id.taskName);
        taskDateText.setText(Integer.toString(task.get_dateTask()));
        taskNameText.setText(task.get_taskName());

        return convertView;
    }
}
