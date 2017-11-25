package com.dinh.clock;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 11/17/2017.
 */

public class CustomListAlarm extends ArrayAdapter<ItemListAlarm> {

    Activity context = null;
    ArrayList<ItemListAlarm> arrayList = null;
    int layoutId;
    LayoutInflater inflater;

    public CustomListAlarm(Activity context, int layoutId, ArrayList<ItemListAlarm> arrayList){
        super(context,layoutId,arrayList);
        this.context = context;
        this.layoutId = layoutId;
        this.arrayList = arrayList;
        this.inflater = context.getLayoutInflater();
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        ItemListAlarm item = arrayList.get(position);

        if(convertView == null){
            viewHolder =  new ViewHolder();
            convertView = inflater.inflate(layoutId, null);
            viewHolder.textTime = (TextView)convertView.findViewById(R.id.textAlarmTime);
            viewHolder.textLapLai = (TextView)convertView.findViewById(R.id.textAlarmSoLan);
            viewHolder.textTen = (TextView)convertView.findViewById(R.id.textAlarmTen);
            viewHolder.nutBat = (Switch)convertView.findViewById(R.id.btnAlarmStart);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        if(arrayList.size()>0 && position>=0){
            viewHolder.textTime.setText(String.valueOf(item.gio)+":"+String.valueOf(item.phut));
            viewHolder.textTen.setText(item.ten);
            viewHolder.textLapLai.setText(item.lapLai);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setUpListView(position);
            }
        });

        return convertView;
    }

    public void setUpListView(int i){
        Intent intent = new Intent(context, SettingAlarm.class);
        context.startActivity(intent);
    }
}

class ViewHolder{
    TextView textTime, textLapLai, textTen;
    Switch nutBat;
    public ViewHolder(){
    }
}