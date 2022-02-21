package com.example.random;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "book";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myBooks";
    private static final String ID_COL = "id";
    private static final String BOOK_COL = "book";
    private static final String AUTHOR_COL = "author";
    private static final String GENRE_COL = "genre";
    private static final String TYPE_COL = "type";
    private static final String DATE_COL = "date";
    private static final String AGE_COL_CHILD = "ageChild";


    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BOOK_COL + " TEXT,"
                + AUTHOR_COL + " TEXT,"
                + GENRE_COL + " TEXT,"
                + TYPE_COL + " TEXT,"
                + DATE_COL + " TEXT ,"
                + AGE_COL_CHILD + " TEXT) ";
        db.execSQL(query);

    }

    public void addNewBook(String bookName,
                           String authorName,
                           String genre,
                           String type,
                           String date,
                           String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BOOK_COL,bookName);
        values.put(AUTHOR_COL,authorName);
        values.put(GENRE_COL,genre);
        values.put(TYPE_COL,type);
        values.put(DATE_COL,date);
        values.put(AGE_COL_CHILD,age);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<DataFile> readBooks(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorBooks = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<DataFile> bookDataArrayList = new ArrayList<>();
        if (cursorBooks.moveToFirst()){
            do {
                bookDataArrayList.add(new DataFile(cursorBooks.getString(1),
                        cursorBooks.getString(2),
                        cursorBooks.getString(3),
                        cursorBooks.getString(4),
                        cursorBooks.getString(5),
                        cursorBooks.getString(6)));
            }while (cursorBooks.moveToNext());
        }

        cursorBooks.close();
        return bookDataArrayList;
    }
    public void updateBook(String bookNameCons,String bookName,
                           String authorName,
                           String genre,
                           String type,
                           String date,
                           String age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(BOOK_COL,bookName);
        values.put(AUTHOR_COL,authorName);
        values.put(GENRE_COL,genre);
        values.put(TYPE_COL,type);
        values.put(DATE_COL,date);
        values.put(AGE_COL_CHILD,age);
        db.update(TABLE_NAME,  values, "book=?", new String[]{bookNameCons});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
