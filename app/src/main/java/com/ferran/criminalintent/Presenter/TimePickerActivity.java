package com.ferran.criminalintent.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.GregorianCalendar;

public class TimePickerActivity extends SingleFragmentActivity {
    private static final String EXTRA_TIME = "extra_time";

    @Override
    protected Fragment createFragment() {
        GregorianCalendar time = (GregorianCalendar) getIntent().getSerializableExtra(EXTRA_TIME);
        return TimePickerFragment.newInstance(time);
    }

    public static Intent newIntent(Context context, GregorianCalendar time) {
        Intent intent = new Intent(context, TimePickerActivity.class);
        intent.putExtra(EXTRA_TIME, time);
        return intent;
    }

}
