package com.week.app.app160806.member;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-08-06.
 */
public class MemberDAO extends SQLiteOpenHelper{
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String EMAIL = "email";
    public static final String ADDR = "addr";

    public MemberDAO(Context context) {
        super(context, "hanbitdb", null, 1);
        this.getWritableDatabase();
        Log.d ("DAO 진입 여부","===== OK =====");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //id, pw, name, phone, email, addr
        //table 생성 : create table TEST(ID text, PW int);
        //table 값 입력 : insert into TEST (ID, PW) values ('hong', 1);
        //table delete : drop table if exists TEST;
        //table 검색 : select ID, PW from TEST where ID = 'hong';
        db.execSQL("create table if not exists member(" +
                " id text primary key," +
                " pw text," +
                " name text," +
                " phone text," +
                " email text," +
                " addr text);");
        db.execSQL("insert into member(id, pw, name, phone, email, addr) " +
                " values ('hong', '1', 'gildong', '010-6888-1502', 'jaekeun@naver.com', '37.5586830,126.8375950');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists member;");
        this.onCreate(db);
    }

    public MemberBean login (MemberBean member){
        Log.d("서비스:Login ID 체크", member.getId());
        MemberBean result = new MemberBean();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select id, pw from member where id = '"+member.getId()+"'", null); //실행순서
        if (cursor.moveToNext()) {
            result.setId(cursor.getString(0));
            result.setPw(cursor.getString(1));
            Log.d("Cursor내 Login ID 체크", result.getId());
        }else{
            result.setId("NONE"); //id가 존재하지 않음
            Log.d("Cursor내 Login ID 체크", "id가 존재하지 않음");
        }
        //db.close();
        return result;
    }
    public void join (MemberBean member){
        Log.d("서비스:Join ID 체크", member.getId());
        String sql = "insert into " +
                String.format("member(%s, %s, %s, %s, %s, %s) ", ID, PW, NAME, PHONE, EMAIL, ADDR) +
                String.format(" values ('%s', '%s', '%s', '%s', '%s', '%s');",
                        member.getId(), member.getPw(), member.getName(),
                        member.getPhone(), member.getEmail(), member.getAddr());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    public MemberBean findById(String id){
        Log.d("DAO findById로 진입 ",id);
        String sql="select " +
                String.format("%s, %s, %s, %s, %s, %s ", ID, PW, NAME, PHONE, EMAIL, ADDR)
                + " from member " +
                String.format(" where id = '%s';", id);
        SQLiteDatabase db = this.getReadableDatabase();
        db.rawQuery(sql, null);
        MemberBean result = new MemberBean();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            result.setId(cursor.getString(0));
            result.setPw(cursor.getString(1));
            result.setName(cursor.getString(2));
            result.setPhone(cursor.getString(3));
            result.setEmail(cursor.getString(4));
            result.setAddr(cursor.getString(5));
        } else {
            result.setId("NONE");
        }
        //db.close();
        return result;
    }
    public int count(){
        return 0;
    } //회원 수
    public ArrayList<MemberBean> list(){
        String sql = "select " +
                String.format("%s, %s, %s, %s, %s, %s ", ID, PW, NAME, PHONE, EMAIL, ADDR)
                + " from member; ";
        ArrayList<MemberBean> temp = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null) {
            Log.d("목록조회", "성공!");
            cursor.moveToFirst();//여러개 record를 가져올때
        }
        do {
            MemberBean result = new MemberBean();
            result.setId(cursor.getString(0));
            result.setPw(cursor.getString(1));
            result.setName(cursor.getString(2));
            result.setPhone(cursor.getString(3));
            result.setEmail(cursor.getString(4));
            result.setAddr(cursor.getString(5));
            temp.add(result);
        } while(cursor.moveToNext());

        //db.close();
        return temp;
    } //전체
    public ArrayList<MemberBean> findByName(String name){
        String sql = "select " +
                String.format("%s, %s, %s, %s, %s ", ID, NAME, PHONE, EMAIL, ADDR)
                + " from member where name like '%"+name+"%';";
        ArrayList<MemberBean> temp = new ArrayList<MemberBean>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor != null) {
            Log.d("이름 조회", "성공!");
            cursor.moveToFirst();//여러개 record를 가져올때
        }
        do {
            MemberBean result = new MemberBean();
            result.setId(cursor.getString(0));
            result.setName(cursor.getString(1));
            result.setPhone(cursor.getString(2));
            result.setEmail(cursor.getString(3));
            result.setAddr(cursor.getString(4));
            temp.add(result);
        } while(cursor.moveToNext());
        //db.close();
        return temp;
    } //이름으로 검색

    //UPDATE
    public void update(MemberBean member){
        Log.d("DAO update 진입: ", member.getPw());
        String sql = "update member " +
                String.format(" set email = '%s', addr = '%s', pw = '%s' where id = '%s'"
                        , member.getEmail()
                        , member.getAddr()
                        , member.getPw()
                        , member.getId());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    //DELETE
    public void delete(String id){
        Log.d("DAO delete 진입 ID 체크", id);
        String sql = "delete from member where id = '"+id+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
}
