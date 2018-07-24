package com.example.gagandeepchugh.falldetection;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        onTaskRemoved(intent);
        Toast.makeText(getApplicationContext(),"This is service",Toast.LENGTH_SHORT).show();



        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartService=new Intent(getApplicationContext(), this.getClass());
        restartService.setPackage(getPackageName());
        startService(restartService);



        super.onTaskRemoved(rootIntent);
    }
}
