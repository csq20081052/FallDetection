package com.example.gagandeepchugh.falldetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class insert extends AppCompatActivity {

   DatabaseHelper myDb;
    EditText ed1,ed2;
    ImageButton bt1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        myDb=new DatabaseHelper(this);
        ed1=(EditText)findViewById(R.id.ed1);
        ed2=(EditText)findViewById(R.id.ed2);
        bt1=(ImageButton)findViewById(R.id.butn1);
        AddData();
    }
    public void AddData()
    {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(ed1.getText().toString(),ed2.getText().toString());
                if(isInserted == true)
                    Toast.makeText(insert.this,"Number Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(insert.this,"Number Not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }
}