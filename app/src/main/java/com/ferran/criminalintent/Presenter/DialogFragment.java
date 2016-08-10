package com.ferran.criminalintent.Presenter;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.ferran.criminalintent.R;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogFragment extends Fragment {
    private static final String ARG_IMAGE_PATH = "imagepath";
    private int mImageWidth;
    private int mImageHeight;
    private File mPhotoPath;
    private ImageView mImageShow;

    public DialogFragment() {
        // Required empty public constructor
    }

    public static DialogFragment newInstance(File imagePath) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_IMAGE_PATH, imagePath);
        DialogFragment fragment = new DialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPhotoPath = (File) getArguments().getSerializable(ARG_IMAGE_PATH);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        mImageShow = (ImageView) v.findViewById(R.id.fragment_dialog_crime_picture);
        final ViewTreeObserver viewTreeObserver = mImageShow.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mImageWidth = mImageShow.getWidth();
                mImageHeight = mImageShow.getHeight();
                mImageShow.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        Bitmap bitmap
                = PictureUtils.getScaledBitmap(mPhotoPath.getPath(), mImageWidth, mImageHeight);
        mImageShow.setImageBitmap(bitmap);
        return v;
    }

}
