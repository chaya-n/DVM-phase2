package com.example.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "history.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE HISTORY (ID INTEGER PRIMARY KEY AUTOINCREMENT, HIST VARCHAR(100))";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(String input){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        String tableName="HISTORY";
        cv.put("HIST",input);
        db.insert(tableName,null, cv);

    }

    public List<String> getHistory() {
        List<String> returnList =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM HISTORY";
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                String history= cursor.getString(1);
                returnList.add(history);
            }
            while (cursor.moveToNext());
        }
        return returnList;
    }

    public void clearHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query="DELETE FROM HISTORY";
        db.execSQL(query);
    }

    public String displayMostRecent() {
        SQLiteDatabase db =  this.getWritableDatabase();
        String query= "SELECT * FROM HISTORY ORDER BY ID DESC LIMIT 1";
        Cursor cursor= db.rawQuery(query,null);
        String s ;
        System.out.println("______________________________________________________________");
        System.out.println(cursor);
        Log.d("Mytag","_______________________");
        Log.d("Mytag",cursor.toString());
        if(cursor.moveToFirst()){
            do {
                s = cursor.getString(1);
                Log.d("Mytag",s);
                return s;
            }
            while (cursor.moveToNext());
        }
        return "";
    }

}
