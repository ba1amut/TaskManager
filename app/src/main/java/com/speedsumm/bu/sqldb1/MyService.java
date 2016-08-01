package com.speedsumm.bu.sqldb1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by bu on 29.07.2016.
 */
public class MyService extends Service{

    String LOG = getClass().getSimpleName();





    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG,"onDestroy сервис");
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Log.d(LOG,"onStartCommand сервис");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                long endTime = intent.getLongExtra("endTime",System.currentTimeMillis()+1*1000);
                while(System.currentTimeMillis()<endTime){
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Message msg = new Message();
                msg.what = 0;
                msg.obj = intent.getStringExtra("taskName");
                Log.d(LOG, "Время настало");
                MainActivity.mHandler.sendMessage(msg);

//
            }
        });
        thread.start();

        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onCreate() {
        Log.d(LOG,"onCreate сервис");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
