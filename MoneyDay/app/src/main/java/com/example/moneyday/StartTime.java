package com.example.moneyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class StartTime extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button Close, Registration;
    TimePicker sTimePicker;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);

        Close = (Button)findViewById(R.id.button_clo);
        Registration = (Button) findViewById(R.id.button_regi);
        sTimePicker = (TimePicker)findViewById(R.id.startTime);

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
                    hour = sTimePicker.getHour();
                    min = sTimePicker.getMinute();
                }
                else {
                    hour = sTimePicker.getCurrentHour();
                    min = sTimePicker.getCurrentMinute();
                }

                Intent t = getIntent();
                String IDay = t.getStringExtra("IDay");

                Intent s = new Intent(StartTime.this, EndTime.class);
                String sH = String.valueOf(hour);
                String sM = String.valueOf(min);
                s.putExtra("sH", sH);
                s.putExtra("sM", sM);
                s.putExtra("IDay", IDay);
                startActivityForResult(s, 1);
//                startActivity(s);
//                startActivityForResult(s, 1);
//                Toast.makeText(getApplicationContext(), "" + hour + min, Toast.LENGTH_LONG).show();
//                TextView sTv = (TextView) findViewById(R.id.sTv);
//                sTv.setText(hour + "시 " + min + "분");

                finish();
            }

        });

    }

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        // 현재 시간을 초기값으로 설정
//        final Calendar c = Calendar.getInstance();
//        int hour = c.get(Calendar.HOUR_OF_DAY);
//        int minute = c.get(Calendar.MINUTE);
//
//        TimePickerDialog tpd = new TimePickerDialog(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
//                this, hour, minute, false);
//
//        return tpd;
//    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

//        TextView sTv = (TextView) findViewById(R.id.sTv);
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
//        sTv.setText(String.valueOf(hourOfDay) + "시 " + String.valueOf(minute) + "분");
    }
}
