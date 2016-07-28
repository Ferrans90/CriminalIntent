package com.ferran.criminalintent.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
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
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {
    private static final String ARG_CRIME_ID = "crime_id";
    private static final String EXTRA_CRIME_TITLE = "com.ferran.criminalintent.Presenter.crime_title";
    private static final String EXTRA_CRIME_IS_SOLVED = "com.ferran.criminalintent.Presenter.crime_issolved";
    private Crime mCrime;
    private EditText mTitleText;
    private Button mDateButton;
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
        String data = new SimpleDateFormat("EEEE, MMM dd, yyyy, HH:mm").format(mCrime.getDate());
        mDateButton.setText(data);
        mDateButton.setEnabled(false);
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
