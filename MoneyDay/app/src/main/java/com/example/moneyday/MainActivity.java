package com.example.moneyday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

//    MyDBOpenHelper2 mydb2;
//    SQLiteDatabase mdb2;
//    Cursor cursor2;    // Table의 모든 record를 순서대로 하나씩 point하면서 읽어오기 위한 변수
//    SimpleCursorAdapter ca2;   // record에 있는 칼럼값들을 하나씩 ListView에 연결시켜주는 변수

    TextView tvDate;
    GridAdapter gridAdapter;
    ArrayList<String> dayList;
    GridView gridView;
    Calendar  mCal;
    Calendar  mCalToday;
    Button Cal;

//    private static final int RQCODE_INSERT2 = 1;
//    private static final int RQCODE_UPDATE2 = 2;
//    private static final int RQCODE_DELETE2 = 3;
//
//    private static final int MENU_INSERT2 = Menu.FIRST;
//    private static final int MENU_UPDATE2 = Menu.FIRST + 1;
//    private static final int MENU_DELETE2 = Menu.FIRST + 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        Intent a = new Intent(MainActivity.this, PopDate.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                    else
                    {
                        String MDate = thisyear + "-" + "0" + thismonth + "-" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(MainActivity.this, PopDate.class);
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
                        Intent a = new Intent(MainActivity.this, PopDate.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                    else
                    {
                        String MDate = thisyear + "-" + thismonth + "-" + dayL;
//                    Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
                        Intent a = new Intent(MainActivity.this, PopDate.class);
                        a.putExtra("MDate", MDate);

                        startActivity(a);
                    }
                }

//                String MDate = thisyear + "-" + thismonth + "-" + dayList.get(i);
//                Toast.makeText(getApplicationContext(), MDate, Toast.LENGTH_LONG).show();
//                Intent a = new Intent(MainActivity.this, PopDate.class);
//                a.putExtra("MDate", MDate);
////                a.putExtra("year", thisyear);
////                a.putExtra("month", thismonth);
////                a.putExtra("day", dayList.get(i));
//
//                startActivity(a);
            }
        });

//        Intent a = getIntent();
//        String IDay = a.getStringExtra("InDate");
//
//        String strCol[] = {"STime", "ETime"};
//        // DB 생성 (this : Income, "Income.db" : 생성하는 DB 이름, null : Android가 제공하는 Cursor객체 사용한다는 뜻, 1 : DB Version)
//        mydb2 = new MyDBOpenHelper2(this, "income.db", null, 1);
//
//        // getWritableDatabase() : 읽고 쓰기 위해 DB를 열음
//        mdb2 = mydb2.getWritableDatabase();
//
//        // rawQuery() : Select 쿼리문 실행하는 메소드 -> Select : 결과값 반환하는 쿼리문 ("SELECT * FROM memo WHERE MDate like '"+RDate+"%';", null);
//        cursor2 = mdb2.rawQuery("SELECT * FROM income WHERE InDate like '%"+IDay+"%';", null);  // 모든 record를 가져옴
//        startManagingCursor(cursor2);
//
//        ca2 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor2, strCol,
//                new int[] {android.R.id.text1, android.R.id.text2});

//        gridView.setAdapter(ca);    // setAdapter() : gridView에 SimpleCursorAdapter연결

//        Intent a = getIntent();
//        String year = a.getStringExtra("year");
//        String month = a.getStringExtra("month");
//        String day = a.getStringExtra("day");
//        String MDate = year + "-" + month + "-" + day;
//        if(MDate != )
//        gridView.setOnItemClickListener(MainActivity.this);   // gridView 클릭 시 화면에 출력
//        registerForContextMenu(gridView);

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
        public Date selectedDate;

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
                convertView = inflater.inflate(R.layout.item_calendar_gridview, parent, false);
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

            // 수입 입력한 날짜에는 동전 이미지 보여주기

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

    public void Calculator(View v) {

        Intent c = new Intent(MainActivity.this, PopCalculator.class);
        startActivity(c);

    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        // requestCode : RQCODE_UPDATE가 넘어옴, resultCode : RESULT_OK가 넘어옴)
//
//        int id = data.getIntExtra("_id", -1);
//        String str1 = data.getStringExtra("WName");
//        String st = data.getStringExtra("STime");
//        String et = data.getStringExtra("ETime");
//        String pay = data.getStringExtra("Pay");
//        String inday = data.getStringExtra("InDate");
//        Toast.makeText(this, "***********"+str1 + st+et+pay+inday, Toast.LENGTH_LONG).show();
////        Intent a = getIntent();
////        String MDate = a.getStringExtra("MDate");
//
////        ContentValues row;
//        if(RESULT_OK == resultCode)
//        {
//            switch (requestCode)
//            {
//                case RQCODE_INSERT2:
//                    mdb2.execSQL("INSERT INTO income VALUES (null, '"+str1+"', '"+st+"', '"+et+"', '"+pay+"', '"+inday+"');");
//                    Toast.makeText(this, str1 + st+et+pay+inday, Toast.LENGTH_LONG).show();
//
//                    break;
//                case RQCODE_UPDATE2:
//                    mdb2.execSQL("UPDATE income SET WName = '"+str1+"', STime = '"+st+"', ETime = '"+et+"', Pay = '"+pay+"' WHERE _id = "+id+";");
//                    break;
//            }
//            cursor2.requery();
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//    }
//
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//
//        if(v == gridView)
//        {
//            menu.add(Menu.NONE, MENU_DELETE2, Menu.NONE, "delete");
//        }
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//
//        Intent i = new Intent(this, Income.class);
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//        int position = info.position;   // 선택한 Item의 위치 저장
//        cursor2.moveToPosition(position);
//        int id = cursor2.getInt(0);   // Cursor 객체가 가리키고 있는 0번 칼럼인 _id 번호 저장
//        String WName = cursor2.getString(1);  // Cursor 객체가 가리키고 있는 1번 칼럼인 Contents 문자열 저장
//        String STime = cursor2.getString(2);
//        String ETime = cursor2.getString(3);
//        String Pay = cursor2.getString(4);
//        String InDate = cursor2.getString(5);
//
//        i.putExtra("WName", WName);
//        i.putExtra("STime", STime);
//        i.putExtra("_id", id);
//        i.putExtra("ETime", ETime);
//        i.putExtra("Pay", Pay);
//        i.putExtra("InDate", InDate);
//
//        switch (item.getItemId())
//        {
//            case MENU_DELETE2:
//                mdb2.execSQL("DELETE FROM income WHERE _id = "+id+";");
//                cursor2.requery();
//                break;
//        }
//
//        return super.onContextItemSelected(item);
//    }
//
//    @Override    // 이벤트 구현(View : 이벤트가 발생한 view(lv))
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//        Intent it = new Intent(this, Income.class);
//        cursor2.moveToPosition(i);    // moveToPosition(i) : 임의의 위치로 Cursor 객체 이동
//        // 칼럼 번호는 0번부터 시작, record 번호는 1번부터 시작
//        int _id = cursor2.getInt(0);
//        String WName = cursor2.getString(1);
//        String STime = cursor2.getString(2);
//        String ETime = cursor2.getString(3);
//        String Pay = cursor2.getString(4);
//        String InDate = cursor2.getString(5);
//
////        String year = Date.substring(0,4);
////        String month = Date.substring(5,7);
////        String day = Date.substring(8,10);
////        String time = Date.substring(11, 19);
////        Toast.makeText(this, year + "-" + month + "-" + day + "\n" + time + "\n" + MDate, Toast.LENGTH_LONG).show();
//
//        // putExtra() : 데이터 삽입
//        it.putExtra("WName", WName);
//        it.putExtra("STime", STime);
//        it.putExtra("_id", _id);
//        it.putExtra("ETime", ETime);
//        it.putExtra("Pay", Pay);
//        it.putExtra("InDate", InDate);
//
//        startActivityForResult(it, RQCODE_UPDATE2);
//    }

}


//// DB생성 및 Table 생성
//class MyDBOpenHelper2 extends SQLiteOpenHelper {
//
//    public MyDBOpenHelper2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        // income Table 생성 (_id : 숫자 자동 증가, WName : 근무지 이름, STime : 시작시간, ETime : 종료시간, Pay : 시급, InDate : 작성한 날짜)
//        db.execSQL("CREATE TABLE income (_id INTEGER PRIMARY KEY AUTOINCREMENT, WName TEXT, STime TEXT, ETime TEXT, Pay TEXT, InDate TEXT);");
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//
//}

