package com.example.gagandeepchugh.falldetection;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.jar.Manifest;

public class Start extends AppCompatActivity {

    ImageButton bt1,bt2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        bt1=(ImageButton)findViewById(R.id.bt1);
        bt2=(ImageButton)findViewById(R.id.bt2);




        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Start.this,exist1.class);
                startActivity(intent1);
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Start.this,new1.class);
                startActivity(intent2);
            }
        });
    }



}
