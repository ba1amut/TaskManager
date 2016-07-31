package com.speedsumm.bu.sqldb1;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by bu on 14.07.2016.
 */
public class TimePickDialog extends AppCompatDialogFragment implements TimePickerDialog.OnTimeSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute= calendar.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(getActivity(), this, hour,minute,true);
        return tpd;
    }



    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        TextView txt1 = (TextView)getActivity().findViewById(R.id.expTime);
        Time time = Time.valueOf(hour+":"+minute+":00");
        NewTask.expTimef = time.getTime();
        txt1.setText(hour+":"+minute+":00");


    }
}

