package com.example.gagandeepchugh.falldetection;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagandeep Chugh on 22-05-2018.
 */

public class Listdataadapter extends ArrayAdapter{
    List list=new ArrayList();

    public Listdataadapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    static class LayoutHandler
    {
        TextView ID,NAME,PHONE;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row =convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.raw_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.ID=(TextView)row.findViewById(R.id.text_user_id);
            layoutHandler.NAME=(TextView)row.findViewById(R.id.text_user_name);
            layoutHandler.PHONE=(TextView)row.findViewById(R.id.text_phone);
            row.setTag(layoutHandler);

        }
        else
        {

            layoutHandler=(LayoutHandler) row.getTag();

        }
        Dataprovider dataprovider=(Dataprovider) this.getItem(position);
        layoutHandler.ID.setText((dataprovider.getId()));
        layoutHandler.NAME.setText((dataprovider.getName()));
        layoutHandler.PHONE.setText((dataprovider.getPhone()));


        return row;
    }
}
