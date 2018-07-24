package com.example.gagandeepchugh.falldetection;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class shownum extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase mdb;
    DatabaseHelper db;
    Cursor cursor;
    Listdataadapter listdataadapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownum);
        listView=(ListView) findViewById(R.id.list_view);
        listdataadapter=new Listdataadapter(getApplicationContext(),R.layout.raw_layout);
        listView.setAdapter(listdataadapter);
        db=new DatabaseHelper(getApplicationContext());
        mdb=db.getReadableDatabase();
        cursor=db.getInformation(mdb);
        if(cursor.moveToFirst())
        {
            do{

                String id,name,mob;
                id=cursor.getString(0);
                name=cursor.getString(1);
                mob=cursor.getString(2);
                Dataprovider dataprovider=new Dataprovider(id,name,mob);
                listdataadapter.add(dataprovider);

            }while (cursor.moveToNext());
        }

    }
}