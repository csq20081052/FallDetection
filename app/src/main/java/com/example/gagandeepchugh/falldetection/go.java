package com.example.gagandeepchugh.falldetection;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class go extends AppCompatActivity {
    private final static int SEND_SMS_PERMISSION_REQUEST_CODE=111;

    private SensorManager sensorManager;
    DatabaseHelper mydbs;
    int i,bell = 0;
    double earth_range = 0;
    double earth_prev = 0;
    float[] gravityValues = null, magneticValues = null, earthvalues = new float[3];
    float[] Ri = new float[16], I = new float[16], earthAcc = new float[16], inv = new float[16];
    double y_gra=0;
    double z_gra=0;
    long start  , time;
    int as,dc,fs,fd = 0;
    String ac = null;
    //RelativeLayout rl;
    View rl;
    Button b5,b6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        rl=this.getWindow().getDecorView();

        mydbs=new DatabaseHelper(this);
        b5 = (Button) findViewById(R.id.but1);
        b6 = (Button) findViewById(R.id.but2);
        time = System.currentTimeMillis();

        b6.setEnabled(false);

        if(checkPermission(android.Manifest.permission.SEND_SMS)){
            b6.setEnabled(true);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String smshere=new String();
                Cursor res =mydbs.getAllData();
                String message="Fall has been detected";
                SmsManager manager=SmsManager.getDefault();
                while(res.moveToNext())
                {
                    smshere=res.getString(0);
                    manager.sendTextMessage(smshere,null,message,null,null);
                }




            }
        });

        sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(sensorlistener, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_FASTEST);

    }

    SensorEventListener sensorlistener = new SensorEventListener() {

        public void onAccuracyChanged(Sensor arg0, int arg1) {
        }


        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void onSensorChanged(SensorEvent event) {
            Sensor sensor = event.sensor;
            try {
                switch (sensor.getType()) {
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        magneticValues = event.values;
                        break;
                    case Sensor.TYPE_GRAVITY:
                        y_gra = event.values[1];
                        z_gra = event.values[2];
                        gravityValues = event.values;
                        break;
                }
                if ((gravityValues != null) && (magneticValues != null)
                        && (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)) {
                    float[] deviceRelativeAcceleration = new float[4];
                    deviceRelativeAcceleration[0] = event.values[0];
                    deviceRelativeAcceleration[1] = event.values[1];
                    deviceRelativeAcceleration[2] = event.values[2];
                    deviceRelativeAcceleration[3] = 0;

                    SensorManager.getRotationMatrix(Ri, I, gravityValues, magneticValues);
                    earthAcc = new float[16];




                    android.opengl.Matrix.invertM(inv, 0, Ri, 0);
                    android.opengl.Matrix.multiplyMV(earthAcc, 0, inv, 0, deviceRelativeAcceleration, 0);
                    List<Float> earth_d = new ArrayList<>();



                    if (System.currentTimeMillis() <= time + 30000) {

                        if (earthAcc[2] >50) {//change back to 25
                            as++;
                            start = System.currentTimeMillis();
                            Toast.makeText(go.this, "HOOOODD", Toast.LENGTH_SHORT).show();

                        }


                        if (as >0 && as <= 10){// to remove the case of jumping

                            if(earthAcc[2]<=11)
                            {
                                if (y_gra>7 && z_gra<8) {//8 ki jagah 5 tha
                                    dc++;

                                    // time = System.currentTimeMillis();
                                    //as = 0;
                                }
                            }

                            if (dc == 0)
                            {
                                if (System.currentTimeMillis() >= start + 10000) {
                                    ac = "Fall";

                                    rl.setBackground(getDrawable(R.drawable.alert));
                                    if(fd==0)
                                    {//activate this before final code is submitted
                                        String smshere=new String();
                                        Cursor res =mydbs.getAllData();
                                        String message="Fall has been detected";
                                        SmsManager manager=SmsManager.getDefault();
                                        while(res.moveToNext())
                                        {
                                            smshere=res.getString(0);
                                            manager.sendTextMessage(smshere,null,message,null,null);
                                        }
                                        fd++;
                                            /*try {// to be changed
                                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                                r.play();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }*/


                                    }
                                    /*if(bell<7) {// so that the notification song rings for 7 times
                                        try {// to be changed
                                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                                            r.play();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        bell++;
                                    }*/
                                    //a="5";
                                   /*     Toast.makeText(frame3.this, "ghj", Toast.LENGTH_SHORT).show();
                                    MediaPlayer mediaplayer=MediaPlayer.create(getApplicationContext(),R.raw.alarm1);
                                     mediaplayer.prepare();
                                     mediaplayer.start();
                                        mediaplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                            @Override
                                            public void onCompletion(MediaPlayer mediaPlayer) {
                                                //mediaPlayer.release();
                                                Toast.makeText(frame3.this, "hjghgjhghj", Toast.LENGTH_SHORT).show();
                                                mediaPlayer=null;
                                            }
                                        });*/

                                } else
                                {
                                    if (earthAcc[2] > 50)
                                    {
                                        as++;
                                    }
                                }
                            }
                            if(dc!=0)
                            {
                                //should show a message on screen..
                                //that are you alright
                                // rl.setBackgroundColor(Color.YELLOW);
                                // RelativeLayout h= (RelativeLayout) findViewById(R.id.fr3);
                                rl.setBackground(getDrawable(R.drawable.normal));
                                dc=0;
                                fs=10;
                            }
                        }
                        if (as > 6 && as < 10) {
                            ac = "Walking";
                        } else
                            ac = "Running";

                    } else {
                        time = System.currentTimeMillis();
                        as = 0;
                        fd=0;
                        rl.setBackground(getDrawable(R.drawable.health));

                        // rl.setBackgroundColor(Color.GREEN);
                    }
                    b5.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View view) {
                            time = System.currentTimeMillis();
                            as = 0;
                            fd=0;
                            //rl.setBackgroundColor(Color.GREEN);
                            rl.setBackground(getDrawable(R.drawable.health));
                        }
                    });
                    b6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String smshere=new String();
                            Cursor res =mydbs.getAllData();
                            String message="Fall has been detected";
                            SmsManager manager=SmsManager.getDefault();
                            while(res.moveToNext())
                            {
                                smshere=res.getString(0);
                                manager.sendTextMessage(smshere,null,message,null,null);
                            }


                        }
                    });

                            /*String smshere=new String();
                            Cursor res =mydbs.getAllData();
                            int s=0;
                            String message="Fall has been detected";
                            SmsManager manager=SmsManager.getDefault();
                            while(res.moveToNext())
                            {
                                smshere=res.getString(3);
                                System.out.print(smshere);
                                manager.sendTextMessage(smshere,null,message,null,null);
                                s++;
                            }*/





                }
            } catch (Exception e) {
                Log.e("error", e.toString());
            }
        }
    };

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(sensorlistener);
    }
    private boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case SEND_SMS_PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && (grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    b6.setEnabled(true);
                }
                break;
        }
    }


}










