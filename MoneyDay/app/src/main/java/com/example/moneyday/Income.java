package com.example.moneyday;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Income extends AppCompatActivity {

    EditText et_WName, et_pay;
    TextView t_sTv, t_eTv;
    Button Close, Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        et_WName = (EditText)findViewById(R.id.WName);
        et_pay = (EditText)findViewById(R.id.Pay);
        t_sTv = (TextView)findViewById(R.id.sTv);
        t_eTv = (TextView)findViewById(R.id.eTv);

        // PopIncome 에서 데이터 받음
        Intent i2 = getIntent();
        final String WName = i2.getStringExtra("WName");
        final String Pay = i2.getStringExtra("Pay");
        final String STime = i2.getStringExtra("STime");
        final String ETime = i2.getStringExtra("ETime");

        et_WName.setText(WName);
        et_pay.setText(Pay);
        t_sTv.setText(STime);
        t_eTv.setText(ETime);

        // 시작, 종료 시간 받아옴
        Intent e = getIntent();
        String sH = e.getStringExtra("sH");
        String sM = e.getStringExtra("sM");
        String eH = e.getStringExtra("eH");
        String eM = e.getStringExtra("eM");
        final String IDay = e.getStringExtra("IDay");
        if (sH != null && sM != null)
            t_sTv.setText(sH + ": " + sM);
        else
            t_sTv.getText();
        if (eH != null && eM != null)
            t_eTv.setText(eH + ": " + eM);
        else
            t_eTv.getText();

        final String st = sH + ":" + sM;
        final String et = eH + ":" + eM;

//        Toast.makeText(getApplicationContext(), "st : " + st + et + IDay, Toast.LENGTH_LONG).show();

        // PopDate 에서 작성 날짜 받아옴
//        Intent d = getIntent();
//        final String IDay = d.getStringExtra("IDay");

//        Toast.makeText(getApplicationContext(), WName + Pay + STime + ETime + IDay, Toast.LENGTH_LONG).show();

//        if (Pay == null)
//            et_pay.getText();
//        else
//            et_pay.setText(Pay);
//
//        if (STime == null)
//            t_sTv.getText();
//        else
//            t_sTv.setText(STime);
//
//        if (ETime == null)
//            t_eTv.getText();
//        else
//            t_eTv.setText(ETime);

        Close = (Button)findViewById(R.id.button_clo);
        Registration = (Button) findViewById(R.id.button_regi);

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = getIntent();
                String IDay = e.getStringExtra("IDay");

                Intent r = new Intent(Income.this, PopIncome.class);
//                Intent r = getIntent();
                r.putExtra("WName", et_WName.getText().toString());
                r.putExtra("Pay", et_pay.getText().toString());
                r.putExtra("STime", t_sTv.getText().toString());
                r.putExtra("ETime", t_eTv.getText().toString());
                r.putExtra("InDate", IDay);
//                Toast.makeText(getApplicationContext(), et_WName.getText().toString() + et_pay.getText().toString()
//                        + t_sTv.getText().toString() + t_eTv.getText().toString() + IDay, Toast.LENGTH_LONG).show();
                setResult(RESULT_CANCELED, r);
                finish();
            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e = getIntent();
                String IDay = e.getStringExtra("IDay");

//                Intent r = new Intent(Income.this, PopIncome.class);
                Intent r = getIntent();
                r.putExtra("WName", et_WName.getText().toString());
                r.putExtra("Pay", et_pay.getText().toString());
                r.putExtra("STime", t_sTv.getText().toString());
                r.putExtra("ETime", t_eTv.getText().toString());
                r.putExtra("InDate", IDay);
//                Toast.makeText(getApplicationContext(), "@@" + et_WName.getText().toString() + et_pay.getText().toString()
//                        + t_sTv.getText().toString() + t_eTv.getText().toString() + IDay, Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, r);
                finish();
            }

        });
        // 시작 시간 데이터 얻어옴
//        TextView t_sTv = (TextView) findViewById(R.id.sTv);

//        Intent s = getIntent();
//        String sH = s.getStringExtra("sH");
//        String sM = s.getStringExtra("sM");
//        if (sH != null && sM != null)
//            t_sTv.setText(sH + "시 " + sM + "분");
//        else
//            t_sTv.getText();
//        Toast.makeText(getApplicationContext(), "" + H + M, Toast.LENGTH_LONG).show();

        // 종료 시간 데이터 얻어 옴
//        TextView t_eTv = (TextView) findViewById(R.id.eTv);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(KeyEvent.KEYCODE_BACK == keyCode)
        {
            setResult(RESULT_CANCELED, getIntent());
        }
        return super.onKeyDown(keyCode, event);
    }

    public void OnStart(View v) {
//        StartTime startTime = new StartTime();
//        startTime.show(getSupportFragmentManager(), "TimePicker");

//        // PopDate 에서 작성 날짜 받아옴
//        Intent d = getIntent();
//        String IDay = d.getStringExtra("IDay");
//
//        Intent t = new Intent(Income.this, StartTime.class);
//        t.putExtra("IDay", IDay);
////        startActivity(t);
//        startActivityForResult(t, 1);
//
////        startActivityForResult(new Intent(this, StartTime.class), 1);
//        finish();

        // TimePicker 보여주기
        DialogFragment newFragment = new StartT();
        newFragment.show(getSupportFragmentManager(), "StartTime");

    }

    public void OnEnd(View v) {

//        // PopDate 에서 작성 날짜 받아옴
//        Intent d = getIntent();
//        String IDay = d.getStringExtra("IDay");
//
//        Intent t = new Intent(Income.this, EndTime.class);
//        t.putExtra("IDay", IDay);
////        startActivity(t);
//        startActivityForResult(t, 1);
////        startActivityForResult(new Intent(this, EndTime.class), 1);
//        finish();

        DialogFragment newFragment = new EndT();
        newFragment.show(getSupportFragmentManager(), "EndTime");

    }

}

