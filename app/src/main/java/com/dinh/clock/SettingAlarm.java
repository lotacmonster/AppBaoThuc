package com.dinh.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SettingAlarm extends AppCompatActivity {


    private Spinner spnCategory, spinnerLapLai, spinnerChonNgay, spinnerCheDo;
    TimePicker time;
    Button buttonLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alarm);
        spnCategory = (Spinner) findViewById(R.id.spinnerTamdung);
        spinnerLapLai = (Spinner)findViewById(R.id.spinnerLapLai);
        spinnerCheDo = (Spinner)findViewById(R.id.spinnerCheDo);
        buttonLuu = (Button)findViewById(R.id.btnLuu);
        time = (TimePicker)findViewById(R.id.timePicker1);

        buttonLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               xuliLuu();
            }
        });

        taoSpinnerLapLai();
        taoSpinnerChonNgay(0);
        taoSpinnerTamDung();
        taoSpinnerCheDo();
    }

    public final void xuliLuu(){
        Intent intent = new Intent(this, Main2Activity.class);
        this.startActivity(intent);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(time.getDrawingTime());
        //Toast.makeText(this, String.valueOf(System.currentTimeMillis())+"---"+String.valueOf(SystemClock.elapsedRealtime()), Toast.LENGTH_LONG).show();
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void taoSpinnerTamDung(){
        List<String> listTamDung = new ArrayList<>();
        for(int i=5;i<=30;i+=5) listTamDung.add(String.valueOf(i) + " phút");
        ArrayAdapter<String> adapterTamDung = new ArrayAdapter(this, R.layout.spinner_tam_dung,listTamDung);
        adapterTamDung.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnCategory.setAdapter(adapterTamDung);
    }

    private void taoSpinnerLapLai(){
        List<String> list = new ArrayList<>();
        list.add("Một lần");
        list.add("Hàng ngày");
        list.add("Chọn thứ");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_tam_dung,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerLapLai.setAdapter(adapter);

        spinnerLapLai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int res =(int) spinnerLapLai.getSelectedItemId();
                taoSpinnerChonNgay(res);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void taoSpinnerChonNgay(int id){
        List<String> list = new ArrayList<>();
    }

    private void taoSpinnerCheDo(){
        List<String> list = new ArrayList<>();
        list.add("Từ vựng");
        list.add("Trò chơi");
        list.add("Giải toán");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.spinner_tam_dung,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerCheDo.setAdapter(adapter);

        spinnerCheDo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}


