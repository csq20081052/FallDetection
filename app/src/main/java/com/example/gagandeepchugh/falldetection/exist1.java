package com.example.gagandeepchugh.falldetection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class exist1 extends AppCompatActivity {

    ImageButton bt1,bt2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist1);

        bt1=(ImageButton) findViewById(R.id.bt1);
        bt2=(ImageButton) findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(exist1.this,go.class);
                startActivity(intent1);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(exist1.this,show.class);
                startActivity(intent2);
            }
        });

    }
}
