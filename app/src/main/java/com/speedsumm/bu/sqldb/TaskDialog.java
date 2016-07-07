package com.speedsumm.bu.sqldb;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by bu on 07.07.2016.
 */
public class TaskDialog extends AppCompatDialogFragment{
    public interface TaskDialogListener{
        public void onDialogPositiveClick(AppCompatDialogFragment dialog,String taskName,String taskDate);
        public void onDialogNegativeClick(AppCompatDialogFragment dialog);
    }

    TaskDialogListener mListener;
    EditText taskName;
    TextView taskDate;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (TaskDialogListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+" must implement NoticeDialogListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.task_dialog,null);
        final EditText taskName = (EditText)dialogView.findViewById(R.id.et_taskName);
        TextView taskDate = (TextView) dialogView.findViewById(R.id.tv_taskDate);


        builder.setView(dialogView)
                .setPositiveButton("хорошо", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        mListener.onDialogPositiveClick(TaskDialog.this,taskName.getText().toString(),"20160707");

                    }
                })
                .setNegativeButton("плохо", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mListener.onDialogNegativeClick(TaskDialog.this);

                    }
                })
                .setTitle("Введите параметры новой задачи");



        return builder.create();



    }


}
