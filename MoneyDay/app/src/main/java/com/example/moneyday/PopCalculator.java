package com.example.moneyday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PopCalculator extends AppCompatActivity {

    TextView tvDate;
    GridAdapter gridAdapter;
    ArrayList<String> dayList;
    GridView gridView;
    Calendar  mCal;
    Calendar  mCalToday;
    Button Cal;

    MyDBOpenHelper2 mydb2;
    SQLiteDatabase mdb2;
    Cursor cursor3;    // Table의 모든 record를 순서대로 하나씩 point하면서 읽어오기 위한 변수
    SimpleCursorAdapter ca2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_calculator);

//        mydb2 = new MyDBOpenHelper2(this, "incom.db", null, 1);
//
//        // getWritableDatabase() : 읽고 쓰기 위해 DB를 열음
//        mdb2 = mydb2.getWritableDatabase();

        // rawQuery() : Select 쿼리문 실행하는 메소드 -> Select : 결과값 반환하는 쿼리문
//        cursor2 = mdb2.rawQuery("SELECT sum(SPay) FROM income;", null);  // 모든 record를 가져옴
//        cursor2 = mdb2.rawQuery("SELECT * FROM income", null);
//        startManagingCursor(cursor2);

//        int SPay = cursor2.getInt(0);
//
//
//        int sp = cursor2.getCount();
//        if (sp != 0) {
//            cursor2.moveToFirst();
//        }
//
//        startManagingCursor(cursor2);
//
//        Toast.makeText(this, "" + SPay , Toast.LENGTH_LONG).show();

        Intent sp = getIntent();
//        String iDate = sp.getStringExtra("InDate");
//        String mo = iDate.substring(0,2);
//        String day = InDate.substring(2,3);
        String month = sp.getStringExtra("month");
        String day = sp.getStringExtra("day");
        int SPay = sp.getIntExtra("SPay", 0);
//        Toast.makeText(getApplicationContext(), "SPay : " + SPay, Toast.LENGTH_LONG).show();
        TextView tPay = (TextView) findViewById(R.id.tPay);
        TextView sPay = (TextView) findViewById(R.id.SPay);
        if(SPay != 0) {
//            tPay.setText(month + "월 " + day + "일의 수입");
            tPay.setText(month + "월 " + day + "일의 수입");
            sPay.setText("￦ " + SPay);
        }
        else {
            tPay.getText();
            sPay.getText();
        }

        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(gridAdapter);

        long now = System.currentTimeMillis();
        final Date date = new Date(now); //연,월,일을 따로 저장

        //gridview 요일 표시
        dayList = new ArrayList<String>();

        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        mCal = Calendar.getInstance();
        mCalToday = Calendar.getInstance();

        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);


        CalenderDay();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int dayL = Integer.parseInt(dayList.get(i));

                if (thismonth < 10)
                {
                    if ( dayL < 10)
                    {
                        String MDate = thisyear + "-" + "0" + thismonth + "-" + "0" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(PopCalculator.this, PopIncome.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                    else
                    {
                        String MDate = thisyear + "-" + "0" + thismonth + "-" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(PopCalculator.this, PopIncome.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }

                }
                else
                {
                    if ( dayL < 10)
                    {
                        String MDate = thisyear + "-" + thismonth + "-" + "0" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(PopCalculator.this, PopIncome.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                    else
                    {
                        String MDate = thisyear + "-" + thismonth + "-" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(PopCalculator.this, PopIncome.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                }

//                String MDate = thisyear + "-" + thismonth + "-" + dayList.get(i);
////                Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
//                Intent a = new Intent(PopCalculator.this, PopIncome.class);
//                a.putExtra("MDate", MDate);
////                a.putExtra("year", thisyear);
////                a.putExtra("month", thismonth);
////                a.putExtra("day", dayList.get(i));
//
//                startActivity(a);
//                finish();

//                Intent sp = getIntent();
//                String SPay = sp.getStringExtra("SPay");
//                Toast.makeText(getApplicationContext(), "SPay : " + SPay, Toast.LENGTH_LONG).show();
//                TextView sPay = (TextView) findViewById(R.id.SPay);
//                sPay.setText("￦ " + SPay);

            }
        });

//        Intent sp = getIntent();
//        String SPay = sp.getStringExtra("SPay");
//        Toast.makeText(this, "SPay : " + SPay, Toast.LENGTH_LONG).show();
//        sPay.setText("￦ " + SPay);

    }


    private void setCalendarDate(int year,int month) {

        mCal.set(Calendar.MONTH,month - 1);
        mCalToday.set(year,month-1,1);

        int startday = mCalToday.get(Calendar.DAY_OF_WEEK);

        if(startday!=1) {
            for (int i = 1; i < startday; i++) {
                dayList.add("");
            }
        }

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            mCalToday.set(mCal.get(Calendar.YEAR),month-1,(i+1));
            dayList.add("" + (i + 1));
        }
        tvDate.setText(year + "년  " + month + "월");
        gridView.setAdapter(gridAdapter);

    }


    private void CalenderDay(){

        int thisYear = mCal.get(Calendar.YEAR);
        int thisMonth = mCal.get(Calendar.MONTH) +1;
        setCalendarDate(thisYear,  thisMonth);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

    }

    private class GridAdapter extends BaseAdapter {
        private final List<String> list;
        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {
            this.list = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_cal_gridview, parent, false);
                holder = new ViewHolder();
                holder.tvItemGridView = (TextView) convertView.findViewById(R.id.tv_item_gridview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tvItemGridView.setText("" + getItem(position));

            mCal.add(mCal.MONTH, +1);
            mCal = Calendar.getInstance();
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String dToday = String.valueOf(today);
            if (thismonth == mCal.get(Calendar.MONTH) + 1) {
                if (dToday.equals(getItem(position))) {  //오늘 day 텍스트 컬러 변경
                    holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            }

            return convertView;
        }

    }

    private void DayList(){

        dayList.clear();
        dayList.add("일");
        dayList.add("월");
        dayList.add("화");
        dayList.add("수");
        dayList.add("목");
        dayList.add("금");
        dayList.add("토");

    }

    private class ViewHolder {
        TextView tvItemGridView;
    }

    Date date = new Date();
    int thisday = date.getDay() + 3;
    int thisyear = date.getYear() + 1900;
    int thismonth = date.getMonth() + 1 ;

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button:
                if(thismonth > 1)
                {
                    thismonth--;
                    DayList();
                    setCalendarDate(thisyear,thismonth);
                }
                else
                {
                    thisyear--;
                    thismonth = 12;
                    DayList();
                    setCalendarDate(thisyear,thismonth);
                }
                break;

            case R.id.button2:
                if(thismonth < 12)
                {
                    thismonth++;
                    //setCalendarDate(thisyear,thismonth);
                    DayList();
                    gridView.setAdapter(gridAdapter);
                    setCalendarDate(thisyear,thismonth);
                }
                else
                {
                    thisyear++;
                    thismonth = 1;
                    DayList();
                    setCalendarDate(thisyear,thismonth);
                }
                break;
        }
    }

    // X 버튼 클릭
    public void mOnClose(View v){
        finish();
    }

    public void onCheckS(View v){

//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//        DatePickerDialog datePickerDialog = new DatePickerDialog(PopCalculator.this, new DatePickerDialog.OnDateSetListener() {
//
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                TextView StartDay = (TextView) findViewById(R.id.StartDay);
//
//                StartDay.setText("시작일 : " + String.valueOf(month) + "- " + String.valueOf(dayOfMonth));
//
//            }
//        }, year, month, day);
//
//        return datePickerDialog;

        DialogFragment sDayF = new SDay();
        sDayF.show(getSupportFragmentManager(), "StartDay");
//        finish();
    }

    public void onCheckE(View v){
        DialogFragment eDayF = new EDay();
        eDayF.show(getSupportFragmentManager(), "EndDay");
//        finish();
    }
}
