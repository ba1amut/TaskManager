package com.speedsumm.bu.sqldb;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bu on 07.07.2016.
 */
public class DatePickDialog extends AppCompatDialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface DatePickDialogListener{
        public void onDialogPositiveClick(AppCompatDialogFragment dialog);
        public void onDialogNegativeClick(AppCompatDialogFragment dialog);
    }

    DatePickDialogListener mListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth= calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),this,year,month,dayOfMonth);
        dpd.setTitle("Введите дату завершения задачи");
        return dpd;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        TextView dateTask = (TextView)getActivity().findViewById(R.id.tv_taskDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yMMd");
        dateTask.setText(dateFormat.format(Calendar.getInstance()));



    }
}
