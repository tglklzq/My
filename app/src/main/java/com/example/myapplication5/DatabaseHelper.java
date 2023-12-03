package com.example.myapplication5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    private static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ACCOUNT = "account";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_USER_IMAGE = "user_image";


    private static final String CREATE_TABLE_USERS = "CREATE TABLE " +
            TABLE_USERS + "(" +
            COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_ACCOUNT + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_USER_NAME + " TEXT, " +
            COLUMN_GENDER + " TEXT, " +
            COLUMN_HEIGHT + " REAL, " +
            COLUMN_WEIGHT + " REAL, " +
            COLUMN_USER_IMAGE + " TEXT" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 升级数据库时的操作，可以根据需要进行处理
    }

    public User getUserData(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                COLUMN_USER_ID,
                COLUMN_ACCOUNT,
                COLUMN_PASSWORD,
                COLUMN_USER_NAME,
                COLUMN_GENDER,
                COLUMN_HEIGHT,
                COLUMN_WEIGHT,
                COLUMN_USER_IMAGE
        };

        String selection = COLUMN_USER_ID + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(
                TABLE_USERS,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            user = new User(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNT)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_HEIGHT)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_WEIGHT)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_USER_IMAGE))
            );
            cursor.close();
        }

        db.close();

        return user;
    }
}
