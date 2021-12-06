package com.example.moneyday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EndTime extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    Button Close, Registration;
    TimePicker eTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);

        Close = (Button)findViewById(R.id.button_clo);
        Registration = (Button) findViewById(R.id.button_regi);
        eTimePicker = (TimePicker)findViewById(R.id.endTime);

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                final Calendar c = Calendar.getInstance();
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//                int min = c.get(Calendar.MINUTE);
                Calendar.getInstance();
                int hour, min;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = eTimePicker.getHour();
                    min = eTimePicker.getMinute();
                }
                else {
                    hour = eTimePicker.getCurrentHour();
                    min = eTimePicker.getCurrentMinute();
                }

                // StartTime에서 시작 시간 데이터 얻어옴
                Intent s = getIntent();
                String sH = s.getStringExtra("sH");
                String sM = s.getStringExtra("sM");
                String IDay = s.getStringExtra("IDay");

                Intent e = new Intent(EndTime.this, Income.class);
//                Intent e = getIntent();
                String eH = String.valueOf(hour);
                String eM = String.valueOf(min);
                e.putExtra("sH", sH);
                e.putExtra("sM", sM);
                e.putExtra("eH", eH);
                e.putExtra("eM", eM);
                e.putExtra("IDay", IDay);
//                startActivity(e);
                startActivityForResult(e, 1);
//                setResult(RESULT_OK, e);
//                startActivityForResult(e, 1);
//                Toast.makeText(getApplicationContext(), sH + sM+ eH + eM, Toast.LENGTH_LONG).show();
//                TextView eTv = (TextView) findViewById(R.id.eTv);
//                eTv.setText(hour + "시 " + min + "분");

                finish();
            }

        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

//        TextView eTv = (TextView) findViewById(R.id.eTv);
//        String aMpM = "AM";
//        if (hourOfDay > 11) {
//            aMpM = "PM";
//        }
//        int currentHour;
//        if (hourOfDay > 11) {
//            currentHour = hourOfDay - 12;
//        }
//        else {
//            currentHour = hourOfDay;
//        }
//
//        eTv.setText(String.valueOf(hourOfDay) + "시 " + String.valueOf(minute) + "분");
    }
}
