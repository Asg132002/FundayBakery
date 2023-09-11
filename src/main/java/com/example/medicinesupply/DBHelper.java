package com.example.medicinesupply;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CakeShopDB.db";
    public static final String TABLE1 = "LoginData";
    public static final String TABLE2 = "OrderData";
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    private static final String CREATE_TABLE1 = "create table LoginData(_id INTEGER primary key autoincrement, email TEXT unique, password TEXT)";

    private static final String CREATE_TABLE2 = String.format("create table OrderData(_id TEXT primary key autoincrement default \"ORD0001\", cakename TEXT, baseprice INTEGER, quantity INTEGER, whippedcream INTEGER, ganache INTEGER, caramel INTEGER, chocochips INTEGER, whitechips INTEGER, sprinklers INTEGER, macaroons INTEGER, flowers INTEGER, totalprice INTEGER, msgcake TEXT)");

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE1);
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        onCreate(db);
    }

    public Boolean insertDataLogin(String email,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Email",email);
        values.put("Password",password);

        long result = db.insert("LoginData",null, values);
        return result != -1;
    }

    public Boolean insertDataOrder(String cakename, int baseprice, int quantity,
                                   int[] arr, int totalprice, String msgcake)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("cakename",cakename);
        values.put("baseprice",baseprice);
        values.put("quantity",quantity);
        values.put("whippedcream",arr[0]);
        values.put("ganache",arr[1]);
        values.put("caramel",arr[2]);
        values.put("chocochips",arr[3]);
        values.put("whitechips",arr[4]);
        values.put("sprinklers",arr[5]);
        values.put("macaroons",arr[6]);
        values.put("flowers",arr[7]);
        values.put("totalprice",totalprice);
        values.put("msgcake",msgcake);

        long result = db.insert("OrderData",null, values);
        return result != -1;
    }
    public Boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LoginData where email=?",new String[] {email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from LoginData where email=? and password=?",new String[] {email,password});
        return cursor.getCount() > 0;
    }

}
