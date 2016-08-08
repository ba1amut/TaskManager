package com.speedsumm.bu.sqldb1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bu on 07.08.2016.
 */
public class MyListViewAdapter extends ArrayAdapter<Task> {


    public MyListViewAdapter(Context context, List<Task> taskList) {
        super(context, 0, taskList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        LayoutInflater inflater =(LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.task_list,parent,false);
        TextView taskName = (TextView)convertView.findViewById(R.id.taskName);
        TextView taskBody = (TextView)convertView.findViewById(R.id.taskBody);
        TextView taskDate = (TextView)convertView.findViewById(R.id.taskDate);

        taskName.setText(task.get_taskName());
        taskBody.setText(task.get_taskBody());
        taskDate.setText(String.valueOf(task.get_expDate()));

        return convertView;
    }
}

