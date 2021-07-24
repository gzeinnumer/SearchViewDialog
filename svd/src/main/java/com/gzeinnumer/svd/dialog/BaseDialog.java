package com.gzeinnumer.svd.dialog;


import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import com.gzeinnumer.svd.R;

public abstract class BaseDialog extends DialogFragment {

    protected double canvasWidth = 0.9;
    protected boolean isFullScreen = false;

    protected int animationStyle = R.style.DialogStyle;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = animationStyle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, animationStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);

        boolean isFullScreen = false;
        if (isFullScreen) {
            getDialog().getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        }
        boolean isCustom = false;
        if (isCustom) {
            getDialog().getWindow().setLayout(CardView.LayoutParams.WRAP_CONTENT, CardView.LayoutParams.WRAP_CONTENT);
        }
    }

    protected abstract int contentView();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(contentView(), container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new Dialog(getActivity(), getTheme()) {
            @Override
            public void onBackPressed() {

            }
        };
    }

    public void onResume() {
        super.onResume();

        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width;
        int height;

        if (isFullScreen) {
            width = WindowManager.LayoutParams.MATCH_PARENT;
            height = WindowManager.LayoutParams.MATCH_PARENT;

            //setLayout(int width, int height)
            window.setLayout(width, height);

        } else {
            width = size.x;
            height = WindowManager.LayoutParams.WRAP_CONTENT;
            //setLayout(int width, int height)
            if (canvasWidth >= 0.1 && canvasWidth <= 1.0) {
                window.setLayout((int) (width * canvasWidth), height);
            } else {
                window.setLayout((int) (width * 0.9), height);
            }
        }
        window.setGravity(Gravity.CENTER);
    }
}
