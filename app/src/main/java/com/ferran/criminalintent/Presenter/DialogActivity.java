package com.ferran.criminalintent.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.io.File;

public class DialogActivity extends SingleFragmentActivity {
    private static final String EXTRA_IMAGE_PATH = "extraimagepath";

    public static Intent newIntent(Context context, File imagePath) {
        Intent intent = new Intent(context, DialogActivity.class);
        intent.putExtra(EXTRA_IMAGE_PATH, imagePath);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        File imagePath = (File) getIntent().getSerializableExtra(EXTRA_IMAGE_PATH);
        return DialogFragment.newInstance(imagePath);
    }
}
