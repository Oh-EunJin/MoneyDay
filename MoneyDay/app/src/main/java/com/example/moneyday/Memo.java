package com.example.moneyday;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Memo extends AppCompatActivity {

    EditText et_Contents;
    Button Close, Registration;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        et_Contents = (EditText)findViewById(R.id.Contents);
        // PopDate 에서 전달한 Intent객체인 it을 받음
        Intent i = getIntent();
        String Contents = i.getStringExtra("Contents");
        et_Contents.setText(Contents);

        Close = (Button)findViewById(R.id.button_clo);
        Registration = (Button) findViewById(R.id.button_regi);

        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("Contents", et_Contents.getText().toString());
                setResult(RESULT_CANCELED, i);
                finish();
            }
        });

        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();
                i.putExtra("Contents", et_Contents.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }

        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(KeyEvent.KEYCODE_BACK == keyCode)
        {
            setResult(RESULT_CANCELED, getIntent());
        }
        return super.onKeyDown(keyCode, event);
    }
}
