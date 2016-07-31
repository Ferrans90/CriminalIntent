package com.ferran.criminalintent.Presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import com.ferran.criminalintent.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimePickerFragment extends DialogFragment {
    private TimePicker mTimePicker;
    private Button mOkButton;
    private static final String EXTRA_TIME = "com.ferran.criminalintent.Presenter.time";
    public static final String RESULT_TIME = "result_time";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final GregorianCalendar time = (GregorianCalendar) getArguments().getSerializable(EXTRA_TIME);
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH);
        int day = time.get(Calendar.DAY_OF_MONTH);
        int hour = time.get(Calendar.HOUR_OF_DAY);
        int minute = time.get(Calendar.MINUTE);

        View v = inflater.inflate(R.layout.dialog_time, container, false);

        mTimePicker = (TimePicker) v.findViewById(R.id.dialog_date_time_picker);
        if (Build.VERSION.SDK_INT >= 23) {
            mTimePicker.setHour(hour);
            mTimePicker.setMinute(minute);
        } else {
            mTimePicker.setCurrentHour(hour);
            mTimePicker.setCurrentMinute(minute);
        }
        mOkButton = (Button) v.findViewById(R.id.time_picker_ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int hour = mTimePicker.getHour();
                    int minute = mTimePicker.getMinute();
                    GregorianCalendar time1 = new GregorianCalendar(time.get(Calendar.YEAR),
                            time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH),
                            hour, minute);
                    sentResult(Activity.RESULT_OK, time1);

                } else {
                    int hour = mTimePicker.getCurrentHour();
                    int minute = mTimePicker.getCurrentMinute();
                    GregorianCalendar time1 = new GregorianCalendar(time.get(Calendar.YEAR),
                            time.get(Calendar.MONTH), time.get(Calendar.DAY_OF_MONTH),
                            hour, minute);
                    sentResult(Activity.RESULT_OK, time1);
                }
                getActivity().finish();
            }
        });
        return v;
    }

    public static TimePickerFragment newInstance(GregorianCalendar time) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TIME, time);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private void sentResult(int resultCode, GregorianCalendar time) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_TIME, time);
        getActivity().setResult(resultCode, intent);
    }

    public static GregorianCalendar getTimePickerResult(Intent intent) {
        return (GregorianCalendar) intent.getSerializableExtra(RESULT_TIME);
    }
}
