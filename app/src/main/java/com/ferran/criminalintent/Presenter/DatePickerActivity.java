package com.ferran.criminalintent.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.GregorianCalendar;

public class DatePickerActivity extends SingleFragmentActivity {
    private static final String EXTRA_DATE = "extra_time";

    @Override
    protected Fragment createFragment() {
        GregorianCalendar date = (GregorianCalendar) getIntent().getSerializableExtra(EXTRA_DATE);
        return DatePickerFragment.newInstance(date);
    }

    public static Intent newIntent(Context context, GregorianCalendar extraDate) {
        Intent intent = new Intent(context, DatePickerActivity.class);
        intent.putExtra(EXTRA_DATE, extraDate);
        return intent;
    }


}
