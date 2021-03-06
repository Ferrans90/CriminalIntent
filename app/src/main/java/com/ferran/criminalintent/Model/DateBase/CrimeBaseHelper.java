package com.ferran.criminalintent.Model.DateBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ferran.criminalintent.Model.DateBase.CrimeDbSchema.Cols;
import com.ferran.criminalintent.Model.DateBase.CrimeDbSchema.CrimeTable;

public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATEBASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATEBASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CrimeTable.NAME + "("
                + " _id integer primary key autoincrement, "
                + Cols.UUID + ", "
                + Cols.TITLE + ", "
                + Cols.DATE + ", "
                + Cols.SOLVED + ", "
                + Cols.SUSPECT + " )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
