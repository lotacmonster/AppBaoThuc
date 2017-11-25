package com.dinh.clock;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton buttonClock, buttonTimer, buttonAlarm;
    ImageView imgHH, imgH, imgMM, imgM;
    Calendar calendar;
    Handler handlerTimer;
    Thread threadTimer;
    TextView textDate;


    private int changed(int number){
        int res;
        switch(number){
            case 1: res= R.mipmap.num_1;break;
            case 2: res= R.mipmap.num_2;break;
            case 3: res= R.mipmap.num_3;break;
            case 4: res= R.mipmap.num_4;break;
            case 5: res= R.mipmap.num_5;break;
            case 6: res= R.mipmap.num_6;break;
            case 7: res= R.mipmap.num_7;break;
            case 8: res= R.mipmap.num_8;break;
            case 9: res= R.mipmap.num_9;break;
            default : res= R.mipmap.num_0;
        };
        return res;
    }

    private String changedDayOfWeek(int number){
        String res;
        switch (number){
            case 2: res = "Thứ Hai"; break;
            case 3: res = "Thứ Ba"; break;
            case 4: res = "Thứ Tư"; break;
            case 5: res = "Thứ Năm"; break;
            case 6: res = "Thứ Sáu"; break;
            case 7: res = "Thứ Bảy"; break;
            default: res = "Chủ Nhật"; break;
        };
        return res;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonClock = (ImageButton)findViewById(R.id.btnClock);
        buttonAlarm = (ImageButton)findViewById(R.id.btnAlarm);
        buttonTimer = (ImageButton)findViewById(R.id.btnTimer);

        buttonAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionAlarm();
            }
        });

        imgHH = (ImageView)findViewById(R.id.imgHH);
        imgH  = (ImageView)findViewById(R.id.imgH);
        imgMM = (ImageView)findViewById(R.id.imgMM);
        imgM  = (ImageView)findViewById(R.id.imgM);

        textDate = (TextView)findViewById(R.id.textDate);

        handlerTimer = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                calendar = Calendar.getInstance();
                imgHH.setImageResource(changed(calendar.get(Calendar.HOUR)/10));
                imgH.setImageResource(changed(calendar.get(Calendar.HOUR)%10));
                imgMM.setImageResource(changed(calendar.get(Calendar.MINUTE)/10));
                imgM.setImageResource(changed(calendar.get(Calendar.MINUTE)%10));

                String date = "";
                date += changedDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)) + ", ";
                date += String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "/";
                date += String.valueOf(calendar.get(Calendar.MONTH)+1) + "/";
                date += String.valueOf(calendar.get(Calendar.YEAR));
                textDate.setText(date);
            }
        };

        threadTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Message msg = handlerTimer.obtainMessage();
                    handlerTimer.sendMessage(msg);
                    SystemClock.sleep(10000);
                }
            }
        });

        threadTimer.start();

    }

    public void actionAlarm(){
        Intent intent = new Intent(this, AlarmActivity.class);
        this.startActivity(intent);
    }

}
