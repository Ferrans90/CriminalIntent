package com.ferran.criminalintent.Model.DateBase;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.ferran.criminalintent.Model.Crime;

import java.util.GregorianCalendar;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {
    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeDbSchema.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        GregorianCalendar tmp = new GregorianCalendar();
        tmp.setTimeInMillis(date);
        crime.setDate(tmp);
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return crime;
    }
}
