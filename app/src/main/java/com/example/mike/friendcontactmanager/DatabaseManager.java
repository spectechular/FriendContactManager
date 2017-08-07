package com.example.mike.friendcontactmanager;

/**
 * Created by Mike on 8/2/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Mike on 7/30/2017.
 */

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "friendDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_FRIENDS = "FRIENDS";
    private static final String ID = "id";
    private static final String EMAIL_ADDRESS = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_FRIENDS + "(" + ID;
        sqlCreate += " integer primary key autoincrement, " + EMAIL_ADDRESS;
        sqlCreate += " text, " + FIRST_NAME + " text, " + LAST_NAME + " text )";
        Log.d("Create Query: ", sqlCreate);
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_FRIENDS);
        onCreate(db);
    }

    // Structure
    // ID || email || first || last

    public void insert(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_FRIENDS;
        sqlInsert += " values (null, '" + friend.getEmail();
        sqlInsert += "', '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName()+ "' )";

        Log.d("--SQL insert query ===" , sqlInsert);

        db.execSQL(sqlInsert);
        db.close();
    }

    public ArrayList<Friend> selectAll() {

        String sqlQuery = "select * from " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Friend> friends = new ArrayList<>();
        while (cursor.moveToNext()) {

            Friend currentFriend = new Friend(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2)
            ,cursor.getString(3));
            friends.add(currentFriend);

            //Debug for getting friends into array
            Log.d("select all from DB: ",currentFriend.toString());

        }

        db.close();


        return friends;
    }

    public void deleteByID(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_FRIENDS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();

    }

    public void updateByID(int id, String email, String firstName, String lastName) {

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set " + EMAIL_ADDRESS + " = '" + email + "', ";
        sqlUpdate += FIRST_NAME + " = '" + firstName + "', ";
        sqlUpdate += LAST_NAME + " = '" + lastName + "'" +  " where " + ID + " = " + id;
        Log.d("UPDATE FRIEND SQL: ",sqlUpdate);

        db.execSQL(sqlUpdate);
        db.close();

    }


    public Friend selectById (int id) {
        String sqlQuery = "select * from " + TABLE_FRIENDS;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        Friend friend = null;
        if (cursor.moveToFirst()) {
            friend = new Friend(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }
        return friend;

    }

    public Friend selectByEmail (String emailToSearch) {

        String sqlQuery = "select * from " + TABLE_FRIENDS;
        sqlQuery += " where " + EMAIL_ADDRESS + " = '" + emailToSearch + "'";

        SQLiteDatabase db = this.getWritableDatabase();

        Friend friend = null;

        Cursor cursor = db.rawQuery(sqlQuery, null);

        if (cursor.moveToFirst() == true) {
            Log.d("In search, email: ", cursor.getString(1));


            friend = new Friend(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            return friend;
        }

        else {

            return friend;

        }

    }






}