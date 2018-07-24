package com.example.gagandeepchugh.falldetection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class deletenum extends AppCompatActivity {
    ImageButton bta1;
    EditText edi1;
    DatabaseHelper mDb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletenum);
        mDb=new DatabaseHelper(this);
        bta1=(ImageButton)findViewById(R.id.bta1);
        edi1=(EditText)findViewById(R.id.edi1);
        DeleteData();
    }
   public void DeleteData() {
        bta1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mDb.deleteData(edi1.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(deletenum.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(deletenum.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

}
