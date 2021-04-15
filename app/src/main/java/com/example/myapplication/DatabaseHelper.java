package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9876543210,'Santu',15000.00,'santu.17@gmail.com','1234567890','SBI0006543')");
        db.execSQL("insert into user_table values(9111234563,'Antu',10582.67,'antu.24@gmail.com','1234567891','SBI0009432')");
        db.execSQL("insert into user_table values(9111234524,'Dibyendu',13556.00,'dibyendu29@gmail.com','1234567892','SBI0004321')");
        db.execSQL("insert into user_table values(9111562685,'Diptangshu',15001.00,'diptangshu@gmail.com','1234567893','SBI0003210')");
        db.execSQL("insert into user_table values(9111234473,'Anu',26048.00,'anu2000@gmail.com','1234567894','SBI0002109')");
        db.execSQL("insert into user_table values(9234562567,'Apu',94516.00,'apu99@gmail.com','1234567895','SBI0001098')");
        db.execSQL("insert into user_table values(9114562463,'Suvam',59360.33,'suvam20@gmail.com','1234567896','SBI0001987')");
        db.execSQL("insert into user_table values(9111233573,'Mantu',85700.22,'mantu07@gmail.com','1234567897','SBI0009876')");
        db.execSQL("insert into user_table values(9112356672,'Imon',43980.46,'imon.07@gmail.com','1234567898','SBI0008765')");
        db.execSQL("insert into user_table values(9110453664,'Dip',27300.00,'dip.12@gmail.com','1234567899','SBI0007654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
