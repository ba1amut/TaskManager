package com.speedsumm.bu.sqldb1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by bu on 14.07.2016.
 */
public class DatePickDialog extends AppCompatDialogFragment implements android.app.DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, year, month, dayOfMonth);
        dpd.setTitle("Введите дату завершения задачи");
        return dpd;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        TextView txt1 = (TextView) getActivity().findViewById(R.id.expDate);
//        txt1.setText("Year: " + view.getYear() + " Month: " + view.getMonth());
        GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        txt1.setText(dateFormat.format(calendar.getTime()));
    }
}

