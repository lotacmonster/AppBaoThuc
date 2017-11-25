package com.dinh.clock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Vector;

public class AlarmActivity extends AppCompatActivity {

    ImageButton buttonClock, buttonAlarm, buttonTimer;
    static ArrayList<ItemListAlarm> arrayList = new ArrayList<ItemListAlarm>();
    static CustomListAlarm listAlarmAdapter = null;
    ListView listAlarm = null;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        buttonClock = (ImageButton)findViewById(R.id.btnClock2);
        buttonAlarm = (ImageButton)findViewById(R.id.btnAlarm2);
        buttonTimer = (ImageButton)findViewById(R.id.btnTimer2);
        buttonAdd   = (Button)findViewById(R.id.btnAddAlarm);

        listAlarm = (ListView)findViewById(R.id.listAlarm);
        listAlarmAdapter = new CustomListAlarm(this, R.layout.customlistview, arrayList);
        listAlarm.setAdapter(listAlarmAdapter);

        buttonClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionClock();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAdd();
            }
        });
    }

    private void actionClock(){
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    private static void actionAdd(){
        arrayList.add(new ItemListAlarm());
        actionChanged(arrayList.size()-1);
        //listAlarmAdapter.notifyDataSetChanged();
    }

    public static void actionChanged(int itemId){
        //Intent intent = new Intent(this, )
    }
}
