package com.speedsumm.bu.sqldb1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by bu on 14.07.2016.
 */
public class NewTask extends AppCompatActivity{
    EditText taskName;
    EditText taskBody;
    TextView expDate;
    Button btnNewTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_dialog);

        taskName = (EditText)findViewById(R.id.taskHeader);
        taskBody= (EditText)findViewById(R.id.taskBody);
        expDate = (TextView)findViewById(R.id.expDate);
        btnNewTask = (Button)findViewById(R.id.btnNewTask);


    }

    public void OnClick(View view) {
        switch (view.getId()){
            case (R.id.expDate):
                DatePickDialog datePickDialog = new DatePickDialog();
                datePickDialog.show(getSupportFragmentManager(),"DateDialog");
                break;
            case (R.id.btnNewTask):
                Intent intent = new Intent();
                intent.putExtra("taskName",taskName.getText().toString());
                intent.putExtra("taskBody",taskBody.getText().toString());
                intent.putExtra("expDate",expDate.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
}
