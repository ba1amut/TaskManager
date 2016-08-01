package com.speedsumm.bu.sqldb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.CallScreeningService;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by bu on 14.07.2016.
 */
public class NewTask extends AppCompatActivity{
    EditText taskName, taskBody;
    TextView expDate,expTime;
    Button btnNewTask;
    Calendar calendar;
    TimeZone timeZone;
    private long expTimeFinal;
    static long expDatef;
    static long expTimef;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_dialog);

        taskName = (EditText)findViewById(R.id.taskHeader);
        taskBody= (EditText)findViewById(R.id.taskBody);
        expDate = (TextView)findViewById(R.id.expDate);
        btnNewTask = (Button)findViewById(R.id.btnNewTask);
        expTime = (TextView)findViewById(R.id.expTime);


    }

    public void OnClick(View view) {
        switch (view.getId()){
            case (R.id.expDate):
                DatePickDialog datePickDialog = new DatePickDialog();
                datePickDialog.show(getSupportFragmentManager(),"DateDialog");
                break;
            case (R.id.btnNewTask):
                calendar = Calendar.getInstance();
                timeZone = calendar.getTimeZone();
                expTimeFinal = expDatef+expTimef+timeZone.getRawOffset();
                Intent intentS = new Intent(getBaseContext(),MyService.class);
                intentS.putExtra("endTime",expTimeFinal);
                intentS.putExtra("taskName",taskName.getText().toString());
                startService(intentS);

                Intent intent = new Intent();
                intent.putExtra("taskName",taskName.getText().toString());
                intent.putExtra("taskBody",taskBody.getText().toString());
                intent.putExtra("expDate",expTimeFinal);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.expTime:
                TimePickDialog timePickDialog = new TimePickDialog();
                timePickDialog.show(getSupportFragmentManager(),"TimeDialog");
                break;
        }
    }
}
