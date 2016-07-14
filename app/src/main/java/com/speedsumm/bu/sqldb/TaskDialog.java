package com.speedsumm.bu.sqldb;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


public class TaskDialog extends AppCompatDialogFragment{
    public interface TaskDialogListener{
         void onDialogPositiveClick(AppCompatDialogFragment dialog,String taskName);

    }

    TaskDialogListener mListener;
    int completed;

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
//        final EditText taskName = (EditText)dialogView.findViewById(R.id.et_taskName);
//
//        builder.setView(dialogView)
//                .setPositiveButton("создать", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mListener.onDialogPositiveClick(TaskDialog.this,taskName.getText().toString());
//                    }
//                })
//                .setNegativeButton("отмена", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                })
//                .setTitle("Введите наименование новой задачи");
        return builder.create();



    }


}
