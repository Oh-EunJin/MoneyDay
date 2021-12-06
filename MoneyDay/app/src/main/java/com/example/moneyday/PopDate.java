package com.example.moneyday;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class PopDate extends AppCompatActivity implements AdapterView.OnItemClickListener{

    MyDBOpenHelper mydb;
    SQLiteDatabase mdb;
    ListView lv;
    Cursor cursor;    // Table의 모든 record를 순서대로 하나씩 point하면서 읽어오기 위한 변수
    SimpleCursorAdapter ca;   // record에 있는 칼럼값들을 하나씩 ListView에 연결시켜주는 변수

    private static final int RQCODE_INSERT = 1;
    private static final int RQCODE_UPDATE = 2;
    private static final int RQCODE_DELETE = 3;

    private static final int MENU_INSERT = Menu.FIRST;
    private static final int MENU_UPDATE = Menu.FIRST + 1;
    private static final int MENU_DELETE = Menu.FIRST + 2;

//    MyDBOpenHelper2 mydb2;
//    SQLiteDatabase mdb2;
//    Cursor cursor2;    // Table의 모든 record를 순서대로 하나씩 point하면서 읽어오기 위한 변수
//    SimpleCursorAdapter ca2;   // record에 있는 칼럼값들을 하나씩 ListView에 연결시켜주는 변수
//
//    private static final int RQCODE_INSERT2 = 4;
//    private static final int RQCODE_UPDATE2 = 5;
//    private static final int RQCODE_DELETE2 = 6;
//
//    private static final int MENU_INSERT2 = Menu.FIRST + 3;
//    private static final int MENU_UPDATE2 = Menu.FIRST + 4;
//    private static final int MENU_DELETE2 = Menu.FIRST + 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_date);

        String strCol[] = {"Contents", "Date"};

        // 메모 DB 생성 (this : PopDate, "menoIn.db" : 생성하는 DB 이름, null : Android가 제공하는 Cursor객체 사용한다는 뜻, 1 : DB Version)
        mydb = new MyDBOpenHelper(this, "menoIn.db", null, 1);

        // getWritableDatabase() : 읽고 쓰기 위해 DB를 열음
        mdb = mydb.getWritableDatabase();

        Intent a = getIntent();
        String RDate = a.getStringExtra("MDate");
        // rawQuery() : Select 쿼리문 실행하는 메소드 -> Select : 결과값 반환하는 쿼리문
        cursor = mdb.rawQuery("SELECT * FROM memo WHERE MDate like '"+RDate+"%';", null);  // 모든 record를 가져옴
        startManagingCursor(cursor);

        String year = RDate.substring(0,4);
        String month = RDate.substring(5,7);
        String day = RDate.substring(8,10);

        TextView MDay = (TextView)findViewById(R.id.Mday);
        MDay.setText(month + "월 " + day + "일의 기록");

        lv = (ListView)findViewById(R.id.listMemo);
        ca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, strCol,
                new int[] {android.R.id.text1, android.R.id.text2});

        lv.setAdapter(ca);    // setAdapter() : ListView에 SimpleCursorAdapter연결

//        Intent a = getIntent();
//        String year = a.getStringExtra("year");
//        String month = a.getStringExtra("month");
//        String day = a.getStringExtra("day");
//        String MDate = year + "-" + month + "-" + day;
//        if(MDate != )
        lv.setOnItemClickListener(PopDate.this);   // listview 클릭 시 화면에 출력
        registerForContextMenu(lv);


//        Intent a = getIntent();
//        String IDay = a.getStringExtra("InDate");

//        String strCol2[] = {"STime", "ETime"};
        // 수입 DB 생성 (this : PopDate, "Income.db" : 생성하는 DB 이름, null : Android가 제공하는 Cursor객체 사용한다는 뜻, 1 : DB Version)
//        mydb2 = new MyDBOpenHelper2(this, "income.db", null, 1);
//
//        // getWritableDatabase() : 읽고 쓰기 위해 DB를 열음
//        mdb2 = mydb2.getWritableDatabase();

        // rawQuery() : Select 쿼리문 실행하는 메소드 -> Select : 결과값 반환하는 쿼리문 ("SELECT * FROM memo WHERE MDate like '"+RDate+"%';", null);
//        cursor2 = mdb2.rawQuery("SELECT * FROM income WHERE InDate like '"+RDate+"%';", null);  // 모든 record를 가져옴
//        cursor2 = mdb.rawQuery("SELECT * FROM income WHERE InDate like '"+RDate+"%';", null);
//        startManagingCursor(cursor2);

//        ca2 = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor2, strCol2,
//                new int[] {android.R.id.text1, android.R.id.text2});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // requestCode : RQCODE_UPDATE가 넘어옴, resultCode : RESULT_OK가 넘어옴)

        // memo
        int id = data.getIntExtra("_id", -1);
        String str1 = data.getStringExtra("Contents");
        Intent a = getIntent();
        String MDate = a.getStringExtra("MDate");
//        ContentValues row;

//        // income
//        int id2 = data.getIntExtra("_id", -1);
//        String name = data.getStringExtra("WName");
//        String st = data.getStringExtra("STime");
//        String et = data.getStringExtra("ETime");
//        String pay = data.getStringExtra("Pay");
//        String inday = data.getStringExtra("InDate");
//        Toast.makeText(this, "***********"+name + st+et+pay+inday, Toast.LENGTH_LONG).show();

        if(RESULT_OK == resultCode)
        {
            switch (requestCode)
            {
                case RQCODE_INSERT:
                    mdb.execSQL("INSERT INTO memo VALUES (null, '"+str1+"', datetime('now', 'localtime'), '"+MDate+"');");
//                    Toast.makeText(this, "", Toast.LENGTH_LONG).show();
                    break;
                case RQCODE_UPDATE:
                    mdb.execSQL("UPDATE memo SET Contents = '"+str1+"', Date = datetime('now', 'localtime') WHERE _id = "+id+";");
                    break;
//                case RQCODE_INSERT2:
////                    mdb2.execSQL("INSERT INTO income VALUES (null, '"+name+"', '"+st+"', '"+et+"', '"+pay+"', '"+inday+"');");
//                    mdb.execSQL("INSERT INTO income VALUES (null, '"+name+"', '"+st+"', '"+et+"', '"+pay+"', '"+inday+"');");
//                    Toast.makeText(this, name + st+et+pay+inday, Toast.LENGTH_LONG).show();
//                    break;
//                case RQCODE_UPDATE2:
////                    mdb2.execSQL("UPDATE income SET WName = '"+name+"', STime = '"+st+"', ETime = '"+et+"', Pay = '"+pay+"' WHERE _id = "+id2+";");
//                    mdb.execSQL("UPDATE income SET WName = '"+name+"', STime = '"+st+"', ETime = '"+et+"', Pay = '"+pay+"' WHERE _id2 = "+id2+";");
//                    break;
            }
            cursor.requery();
//            cursor2.requery();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if(v == lv)
        {
            menu.add(Menu.NONE, MENU_DELETE, Menu.NONE, "delete");
//            menu.add(Menu.NONE, MENU_DELETE2, Menu.NONE, "delete");
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        // memo
        Intent i = new Intent(this, Memo.class);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int position = info.position;   // 선택한 Item의 위치 저장
        cursor.moveToPosition(position);
        int id = cursor.getInt(0);   // Cursor 객체가 가리키고 있는 0번 칼럼인 _id 번호 저장
        String Contents = cursor.getString(1);  // Cursor 객체가 가리키고 있는 1번 칼럼인 Contents 문자열 저장
        String Date = cursor.getString(2);
        String MDate = cursor.getString(3);

        i.putExtra("Contents", Contents);
        i.putExtra("Date", Date);
        i.putExtra("_id", id);
        i.putExtra("MDate", MDate);

//        // income
//        Intent i2 = new Intent(this, Income.class);
//        AdapterView.AdapterContextMenuInfo info2 = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
//        int position2 = info2.position;   // 선택한 Item의 위치 저장
//        cursor2.moveToPosition(position2);
//        int id2 = cursor2.getInt(0);   // Cursor 객체가 가리키고 있는 0번 칼럼인 _id 번호 저장
//        String WName = cursor2.getString(1);  // Cursor 객체가 가리키고 있는 1번 칼럼인 Contents 문자열 저장
//        String STime = cursor2.getString(2);
//        String ETime = cursor2.getString(3);
//        String Pay = cursor2.getString(4);
//        String InDate = cursor2.getString(5);
//
//        i2.putExtra("WName", WName);
//        i2.putExtra("STime", STime);
//        i2.putExtra("_id2", id2);
//        i2.putExtra("ETime", ETime);
//        i2.putExtra("Pay", Pay);
//        i2.putExtra("InDate", InDate);

        switch (item.getItemId())
        {
            case MENU_DELETE:
                mdb.execSQL("DELETE FROM memo WHERE _id = "+id+";");
                cursor.requery();
                break;
//            case MENU_DELETE2:
////                mdb2.execSQL("DELETE FROM income WHERE _id = "+id2+";");
//                mdb.execSQL("DELETE FROM income WHERE _id2 = "+id2+";");
//                cursor2.requery();
//                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override    // 이벤트 구현(View : 이벤트가 발생한 view(lv))
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        // memo
        Intent it = new Intent(this, Memo.class);
        cursor.moveToPosition(i);    // moveToPosition(i) : 임의의 위치로 Cursor 객체 이동
        // 칼럼 번호는 0번부터 시작, record 번호는 1번부터 시작
        String Contents = cursor.getString(1);
        String Date = cursor.getString(2);
        int _id = cursor.getInt(0);
        String MDate = cursor.getString(3);

        String year = Date.substring(0,4);
        String month = Date.substring(5,7);
        String day = Date.substring(8,10);
        String time = Date.substring(11, 19);
//        Toast.makeText(this, year + "-" + month + "-" + day + "\n" + time + "\n" + MDate, Toast.LENGTH_LONG).show();

        // putExtra() : 데이터 삽입
        it.putExtra("Contents", Contents);
        it.putExtra("Date", Date);
        it.putExtra("_id", _id);
        it.putExtra("MDate", MDate);

        startActivityForResult(it, RQCODE_UPDATE);

        // income
//        Intent it2 = new Intent(this, Income.class);
//        cursor2.moveToPosition(i);    // moveToPosition(i) : 임의의 위치로 Cursor 객체 이동
//        // 칼럼 번호는 0번부터 시작, record 번호는 1번부터 시작
//        int _id2 = cursor2.getInt(0);
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
//        it2.putExtra("WName", WName);
//        it2.putExtra("STime", STime);
//        it2.putExtra("_id2", _id2);
//        it2.putExtra("ETime", ETime);
//        it2.putExtra("Pay", Pay);
//        it2.putExtra("InDate", InDate);
//
//        startActivityForResult(it2, RQCODE_UPDATE2);
    }

    //닫기 버튼 클릭
    public void mOnClose(View v){
        finish();
    }

    public void Income(View v){

//        Intent a = getIntent();
//        String RDate = a.getStringExtra("MDate");
//        String year = RDate.substring(0,4);
//        String month = RDate.substring(5,7);
//        String day = RDate.substring(8,10);
//        String IDay = month + "-" + day;
//
//        Intent i = new Intent(PopDate.this, Income.class);
//        i.putExtra("IDay", IDay);
////        Toast.makeText(this, IDay, Toast.LENGTH_LONG).show();
////        startActivity(i);
//        startActivityForResult(i, RQCODE_INSERT2);

        Intent i = new Intent(PopDate.this, PopCalculator.class);
        startActivity(i);
        finish();
    }

    public void Memo(View v){

        startActivityForResult(new Intent(this, Memo.class), RQCODE_INSERT);
//        Intent i2 = new Intent(PopDate.this, Memo.class);
//        startActivity(i2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}

// DB생성 및 Table 생성
class MyDBOpenHelper extends SQLiteOpenHelper {

    public MyDBOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // memo Table 생성 (_id : 숫자 자동 증가, Contents : 내용, Date : 작성한 날짜, MDate : 선택한 날짜)
        db.execSQL("CREATE TABLE memo (_id INTEGER PRIMARY KEY AUTOINCREMENT, Contents TEXT, Date date, MDate TEXT);");
//        db.execSQL("CREATE TABLE income (_id2 INTEGER PRIMARY KEY AUTOINCREMENT, WName TEXT, STime TEXT, ETime TEXT, Pay TEXT, InDate TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

// DB생성 및 Table 생성
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