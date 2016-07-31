package com.speedsumm.bu.sqldb1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * Created by bu on 14.07.2016.
 */
public class MyAdapter extends android.support.v7.widget.RecyclerView.Adapter<MyAdapter.MyViewHolder> implements ItemTouchHelperAdapter {
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
            taskName = (TextView)itemView.findViewById(R.id.taskName);
            taskBody = (TextView)itemView.findViewById(R.id.taskBody);
            expDate = (TextView)itemView.findViewById(R.id.taskDate);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.y\nHH:mm");

        holder.taskName.setText(taskArrayList.get(position).get_taskName());
        holder.taskBody.setText(taskArrayList.get(position).get_taskBody());

        Date date = new Date(taskArrayList.get(position).get_expDate());
        String srtDateTime = simpleDateFormat.format(date);
        holder.expDate.setText(srtDateTime);
    }

    @Override
    public int getItemCount() {
        return taskArrayList.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(taskArrayList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(taskArrayList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        taskArrayList.remove(position);
        notifyItemRemoved(position);

    }

}
