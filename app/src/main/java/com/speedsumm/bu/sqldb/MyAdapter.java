package com.speedsumm.bu.sqldb;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bu on 14.07.2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    ArrayList<Task> taskArrayList;
    MyAdapter(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView taskName;
        public TextView taskBody;
        public TextView expDate;


        public MyViewHolder(View itemView) {
            super(itemView);
            taskName = (TextView)itemView.findViewById(R.id.taskHeader);
            taskBody = (TextView)itemView.findViewById(R.id.taskBody);
            expDate = (TextView)itemView.findViewById(R.id.expDate);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.taskName.setText(taskArrayList.get(position).get_taskName());
        holder.taskBody.setText(taskArrayList.get(position).get_taskBody());
        holder.expDate.setText(taskArrayList.get(position).get_expDate());
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }
}
