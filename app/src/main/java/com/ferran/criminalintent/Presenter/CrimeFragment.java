package com.ferran.criminalintent.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ferran.criminalintent.Model.Crime;
import com.ferran.criminalintent.Model.CrimeLab;
import com.ferran.criminalintent.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String EXTRA_CRIME_TITLE = "com.ferran.criminalintent.Presenter.crime_title";
    private static final String EXTRA_CRIME_IS_SOLVED = "com.ferran.criminalintent.Presenter.crime_issolved";
    private static final String DIALOG_DATE = "DialogDate";
    private static final String DIALOG_TIME = "DialogTime";
    private static final int REQUEST_DATE = 0;
    private static final int REQUEST_TIME = 1;

    private Crime mCrime;
    private EditText mTitleText;
    private Button mDateButton;
    private Button mTimeButton;
    private CheckBox mSolvedCheckBox;

    public CrimeFragment() {
        // Required empty public constructor
    }

    public static CrimeFragment newInstance(UUID crimeId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeID = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeID);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            GregorianCalendar date = DatePickerFragment.getDatePickerResult(data);
            mCrime.setDate(new GregorianCalendar(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                    date.get(Calendar.DAY_OF_MONTH), mCrime.getDate().get(Calendar.HOUR_OF_DAY),
                    mCrime.getDate().get(Calendar.MINUTE)));
            updateDate(mCrime.getDate());
        } else if (requestCode == REQUEST_TIME) {
            GregorianCalendar date = TimePickerFragment.getTimePickerResult(data);
            mCrime.setDate(new GregorianCalendar(mCrime.getDate().get(Calendar.YEAR),
                    mCrime.getDate().get(Calendar.MONTH),
                    mCrime.getDate().get(Calendar.DAY_OF_MONTH),
                    date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE)));
            updateTime(mCrime.getDate());
        }
    }

    private void updateDate(GregorianCalendar date) {
        mDateButton.setText(
                new SimpleDateFormat("EEEE, MMM dd, yyyy").format(date.getTime()));
    }

    private void updateTime(GregorianCalendar date) {
        mTimeButton.setText(new SimpleDateFormat("HH:mm").format(date.getTime()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleText = (EditText) v.findViewById(R.id.crime_title);
        mTitleText.setText(mCrime.getTitle());
        mTitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate(mCrime.getDate());
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DatePickerActivity.newIntent(getActivity(), mCrime.getDate());
                startActivityForResult(intent, REQUEST_DATE);
            }
        });
        mTimeButton = (Button) v.findViewById(R.id.crime_time);
        updateTime(mCrime.getDate());
        mTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TimePickerActivity.newIntent(getActivity(), mCrime.getDate());
                startActivityForResult(intent, REQUEST_TIME);
            }
        });
        //mDateButton.setEnabled(false);
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });

        //returnResult();
        return v;
    }

//    private void returnResult() {
//        Intent intent = new Intent();
//        intent.putExtra(EXTRA_CRIME_TITLE, mTitleText.getText());
//        intent.putExtra(EXTRA_CRIME_IS_SOLVED, mSolvedCheckBox.isChecked());
//        getActivity().setResult(Activity.RESULT_OK, intent);
//    }

//    public static CharSequence changedTitle(Intent intent) {
//        return intent.getCharSequenceExtra(EXTRA_CRIME_TITLE);
//    }
//
//    public static boolean changedSolved(Intent intent) {
//        return intent.getBooleanExtra(EXTRA_CRIME_IS_SOLVED, false);
//    }
}
