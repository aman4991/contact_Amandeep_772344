package com.example.contact_Amandeep_772344_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperPerson extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "PersonDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Person";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_PHONE_NUMBER = "phonenumber";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_EMAIL = "email";

    public DatabaseHelperPerson(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID +" INTEGER NOT NULL CONSTRAINT person_pk PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FIRSTNAME + " varchar(200) NOT NULL," +
                COLUMN_LASTNAME + " varchar(200) NOT NULL," +
                COLUMN_PHONE_NUMBER + " varchar(200) NOT NULL," +
                COLUMN_ADDRESS + " varchar(200) NOT NULL,"+
                COLUMN_EMAIL + " varchar(200) NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }


    boolean addPerson(String firstname , String lastname , String phonenumber , String address, String email){


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //WE NEED TO DEFINE A CONTENT INSTANCE

        ContentValues cv = new ContentValues();

        //THE FIRST ARGUMENT OF THE PUT METHOD IS THE COLUMN NAME AND THE SECOND VALUE IS THE SHOWN AS BELOW

        cv.put(COLUMN_FIRSTNAME, firstname);
        cv.put(COLUMN_LASTNAME, lastname);
        cv.put(COLUMN_PHONE_NUMBER, phonenumber);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_EMAIL, email);

        return   sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1; // It will give row count on success and -1 when get failed
    }

    Cursor getAllPerson(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    boolean updatePerson(int id, String firstname , String lastname , String phonenumber , String address, String email){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRSTNAME,firstname);
        cv.put(COLUMN_LASTNAME, lastname);
        cv.put(COLUMN_PHONE_NUMBER, phonenumber);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_EMAIL, email);
        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    boolean deletePerson(int id){

        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID +"=?", new String[]{String.valueOf(id)}) > 0;
    }
}
