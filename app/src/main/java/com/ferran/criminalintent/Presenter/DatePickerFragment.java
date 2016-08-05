package com.ferran.criminalintent.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.ferran.criminalintent.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerFragment extends android.support.v4.app.DialogFragment {
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "com.ferran.criminalintent.Presenter.criminalintent.date";

    private DatePicker mDatePicker;
    private Button mOkButton;

    public static DatePickerFragment newInstance(GregorianCalendar date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GregorianCalendar date = (GregorianCalendar) getArguments().getSerializable(ARG_DATE);

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        View v = inflater.inflate(R.layout.dialog_date, container, false);
        mDatePicker = (DatePicker) v.findViewById(R.id.dialog_date_date_picker);
        mDatePicker.init(year, month, day, null);
        mOkButton = (Button) v.findViewById(R.id.date_picker_ok_button);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                GregorianCalendar date1 = new GregorianCalendar(year, month, day);
                sendResult(Activity.RESULT_OK, date1);
                getActivity().finish();
            }
        });
        return v;
    }

    private void sendResult(int resultCode, GregorianCalendar date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getActivity().setResult(resultCode, intent);
    }

    public static GregorianCalendar getDatePickerResult(Intent intent) {
        return (GregorianCalendar) intent.getSerializableExtra(EXTRA_DATE);
    }
}
