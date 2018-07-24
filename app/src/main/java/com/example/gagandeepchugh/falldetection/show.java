package com.example.gagandeepchugh.falldetection;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class show extends AppCompatActivity {

    ImageButton bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        bt1=(ImageButton)findViewById(R.id.bt1);
        bt2=(ImageButton) findViewById(R.id.bt2);
        bt3=(ImageButton) findViewById(R.id.bt3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent21=new Intent(show.this,insert.class);
                startActivity(intent21);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent24=new Intent(show.this,shownum.class);
                startActivity(intent24);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent35=new Intent(show.this,deletenum.class);
                startActivity(intent35);
            }
        });
    }
}
