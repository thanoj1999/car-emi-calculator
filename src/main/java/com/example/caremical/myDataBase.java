package com.example.caremical;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDataBase extends SQLiteOpenHelper{
    public static String DATABASE_NAME="Car.db";
    public myDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE CAR_EMI (ID TEXT,NAME TEXT,DATE TEXT,PRINCIPAL_AMOUNT TEXT,DOWN_PAYMENT TEXT,RATE TEXT,YEAR TEXT,EMI TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
